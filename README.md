# Share data structures between ResolverV2 and Backend

To install the library add:

```
 repositories {
        jcenter()
        maven { url "https://jitpack.io" }
 }
 
 dependencies {
         compile 'com.github.sensorberg-dev:sensorberg-common:<currentTag>'
 }
```

If changes are needed, you have to make a new tag after the commit and use this new tag in the target dependecy.

For example, your new tag is 'newTag', then use

	compile 'com.github.sensorberg-dev:sensorberg-common:newTag'