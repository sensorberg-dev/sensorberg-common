package com.sensorberg.entity.common.common.restApi.in;

import static java.util.Collections.singletonList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sensorberg.entity.sync.SyncApplicationRequest;

import lombok.Data;

/**
 * Created by Andreas Dörner on 06.06.16.
 */
@JsonInclude(value=JsonInclude.Include.NON_EMPTY)
@Data
public class RestApiLayoutContext implements Serializable {
    private String id;
    private Date eventDate;
    private long elapsedTime;
    private RestApiLayoutRequest request;
    private SyncApplicationRequest syncApplicationRequest;
    private Date reportedBack;

    public RestApiLayoutContext() {
        id = UUID.randomUUID().toString();
        eventDate = new Date();
    }

    public boolean hasEventsOrActionsOrConversions() {
        if (request != null) {
            if (request.getActivity() != null) {
                if (       !CollectionUtils.isEmpty(request.getActivity().getActions())
                        || !CollectionUtils.isEmpty(request.getActivity().getEvents())
                        || !CollectionUtils.isEmpty(request.getActivity().getConversions())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<RestApiLayoutContext> split(int maxCountOfItems) {
        // "this" must not be modified, because it is the response to the mobile client
        RestApiLayoutContext sampleCtx = createCleanClone();

        // if there is no request or no activity, return immediately
        if (request == null || request.getActivity() == null) {
            return singletonList(sampleCtx);
        }

        RestApiLayoutRequestBody activity = request.getActivity();

        ItemCounts counts = new ItemCounts(activity, maxCountOfItems);
        List<RestApiLayoutContext> result = new ArrayList<>(64);
        while (counts.countOfHandledItems < counts.countOfAllItems) {
            RestApiLayoutContext currentCtx = addAndGetNewContext(sampleCtx, result, counts);

            splitEventItems(activity.getEvents(), counts, currentCtx);
            splitActionItems(activity.getActions(), counts, currentCtx);
            splitConversionItems(activity.getConversions(), counts, currentCtx);
        }

        // should be done in test (to find the right maxCountOfItems:
        // azureEventHubService.checkObjectSize(ctx)
        return result;
    }

    static void splitEventItems(List<RestApiLayoutEvent> events, ItemCounts counts, RestApiLayoutContext ctx) {
        if (counts.countOfHandledEvents < counts.countOfEventItems &&
                counts.countOfCurrentItems < counts.maxCountOfItems) {

            int eventsToHandle = Math.min(counts.countOfEventItems - counts.countOfHandledEvents,
                    counts.maxCountOfItems - counts.countOfCurrentItems);
            ctx.getRequest().getActivity().setEvents(events.subList(counts.countOfHandledEvents, counts.countOfHandledEvents + eventsToHandle));
            counts.handleEvents(eventsToHandle);
        }
    }

    static void splitActionItems(List<RestApiLayoutAction> actions, ItemCounts counts, RestApiLayoutContext ctx) {
        if (counts.countOfHandledActions < counts.countOfActionItems &&
                counts.countOfCurrentItems < counts.maxCountOfItems) {

            int actionsToHandle = Math.min(counts.countOfActionItems - counts.countOfHandledActions,
                    counts.maxCountOfItems - counts.countOfCurrentItems);
            ctx.getRequest().getActivity().setActions(actions.subList(counts.countOfHandledActions, counts.countOfHandledActions + actionsToHandle));
            counts.handleActions(actionsToHandle);
        }
    }

    static void splitConversionItems(List<RestApiLayoutConversion> conversionses, ItemCounts counts, RestApiLayoutContext ctx) {
        if (counts.countOfHandledConversions < counts.countOfConversionItems &&
                counts.countOfCurrentItems < counts.maxCountOfItems) {

            int conversionsToHandle = Math.min(counts.countOfConversionItems - counts.countOfHandledConversions,
                    counts.maxCountOfItems - counts.countOfCurrentItems);

            ctx.getRequest().getActivity().setConversions(conversionses.subList(counts.countOfHandledConversions, counts.countOfHandledConversions + conversionsToHandle));
            counts.handleConversions(conversionsToHandle);
        }
    }

    public static int listSize(List<?> list) {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    private static RestApiLayoutContext addAndGetNewContext(RestApiLayoutContext cleanSample, List<RestApiLayoutContext> contexts, ItemCounts counts) {
        RestApiLayoutContext ctx = cleanSample.cloneContext();
        ctx.id = ctx.id + "-" + (contexts.size()+1);
        contexts.add(ctx);
        counts.countOfCurrentItems = 0;
        return ctx;
    }

    /**
     * Deep cloneContext this context with the following specials:
     * - the response is always null
     * - there are no activities (actions, events or conversions)
     */
    private RestApiLayoutContext createCleanClone() {
        RestApiLayoutContext clone = this.cloneContext();

        // todo: we do not have a response
        //cloneContext.response = null;   //LayoutResponse response must be null in each splits
        clone.setSyncApplicationRequest(null); // ... and so does this.

        if (clone.getRequest() != null && clone.getRequest().getActivity() != null) {
            if (clone.getRequest().getActivity().getActions() != null) {
                clone.getRequest().getActivity().getActions().clear();
            }

            if (clone.getRequest().getActivity().getEvents() != null) {
                clone.getRequest().getActivity().getEvents().clear();
            }

            if (clone.getRequest().getActivity().getConversions() != null) {
                clone.getRequest().getActivity().getConversions().clear();
            }

        }
        return clone;
    }

    private static class ItemCounts {
        int countOfHandledItems;
        int countOfEventItems;
        int countOfActionItems;
        int countOfConversionItems;
        int countOfAllItems;
        int maxCountOfItems;
        int countOfHandledEvents = 0;
        int countOfHandledActions = 0;
        int countOfHandledConversions = 0;
        int countOfCurrentItems = 0;

        private ItemCounts(RestApiLayoutRequestBody activity, int maxCountOfItems) {
            this.countOfEventItems = listSize(activity.getEvents());
            this.countOfActionItems = listSize(activity.getActions());
            this.countOfConversionItems = listSize(activity.getConversions());

            this.countOfAllItems = countOfEventItems + countOfActionItems + countOfConversionItems;
            this.maxCountOfItems = maxCountOfItems;
        }

        private void handleEvents(int count) {
            countOfHandledEvents += count;
            countOfHandledItems += count;
            countOfCurrentItems += count;
        }

        private void handleActions(int count) {
            countOfHandledActions += count;
            countOfHandledItems += count;
            countOfCurrentItems += count;
        }

        private void handleConversions(int count) {
            countOfHandledConversions += count;
            countOfHandledItems += count;
            countOfCurrentItems += count;
        }
    }

    public RestApiLayoutContext cloneContext() {
        return SerializationUtils.clone(this);
    }
}