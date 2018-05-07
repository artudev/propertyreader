# PROPERTY READER

### Rules

* Rule 1: On the home screen display average price in the postcode outward ‘W1F’
* Rule 2: On the home screen display difference in average prices between detached houses and flats
* Rule 3: Create view with list for all the properties with style matching the given design.
* Tech stack: any

### Tech

**Assets**

property-data.json is stored in /assets directory

**External libraries used:**

  * [Butterknife] - Field and method binding for Android views
  * [Timber] - Logging
  * [Gson] - A Java serialization/deserialization library to convert Java Objects into JSON and back
  * [RxJava] - Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM
  * [Dagger] - Fully static, compile-time dependency injection framework

**Design patter:**

* MVP

**Code packages:**

* .parent - Base implementation for Activity and Fragment along with interfaces for them and Presenter. Handles binding for android views, animations, toasts, snackbars, navigation (changing fragments inside one activity). Just the stuff than need to be used everywhere in one place to avoid redundant code.
* .model - Collection of Helpers, Containers and Objects used in project.
* .model.filter - Collection of filters used in project. There are two approaches. We can create each combination of wanted result and search query manualy as a methode (PropertyHelperImpl::averagePriceByPostcode, PropertyHelperImpl::averagePriceByPropertyType), or what I like more create very generic system where we can create Filter instance discribing which Field to look for and under what circumstance we will get a match. .filter package describes second approach (it is still only bone structure of this system needed to fulfill task 1 and 2). In this case model of Property object is needed - fields of Property are described by enum PropertyTextField and PropertyNumericField. Responsibilty of those classes is simply provide generic Name of field (enum) and right type and value from Property (getValue). When model is ready we can finally implement Filters themselves. PropertyRangeFilter and PropertyRegexFilter examples of possible filters. All we need is previosly mentioned Field and in case of PropertyRegexFilter regex. Filter based on given criteria in ::match function will return true/false. I did not spend to much time on this and perhaps it could be done in even simpler way still upholding generic approach.
* .model.property - Model of our core object 'Property' as well as PropertyType (one of the fields of Property which required some handlig - naming) and PropertyHelper. PropertyHelper is in this case another important part of this app as it provides utility to compute all iportant for us calculations. As mentioned in previos entry, PropertyHelper implemens two approaches, one with manually writen methodes to calculate average and other with generic approach where system support variety of Filters. Leaving the approach on side, this class relies heavily on rxjava. All calculations are build into chains of operations and available as a Observables. This way we can simply subscribe to them and perform all heavylifting in the background without freezing the main theard. We can find here also a PropertyContainer which in our main container for List of properties retrieved from file with help of AssetHelper and JsonHelper. It's annotated with Singleton in dagger mainly because we are using it in more than one place and want to avoid re-reading data from storage if it is not needed. However still if we would need to reload this list at some point we can add some custom logic to PropertyContainer::shouldReload to inform our container when to reload and when to skip it if we already have up-to-date data. 
* .home - Activity, Fragment, Contract and Presenter of our Home screen. Contract as an interface describe a relation between Fragment and Presenter of this screen. Presenters are injected with PropertyHelper and PropertyContainer by Dagger.
* .list - Activity, Fragment, Contract and Presenter of our List screen as well as adapter and viewholder of our recuclerview. Contract as an interface describe a relation between Fragment and Presenter of this screen. Presenters are injected with PropertyContainer by Dagger. One can notice that I moved whole String building (header and content of item list) into Property and store once generated strings as members of Property. I did so to further optimize app. As binding happens rather often, constant recreating of same Strings ( 7 beds 2 bathrooms Mansion... ) is wasting our resources. Our Properties are final (no changes happens), once generated header/content won' need altering, we have only one list so no other style will be needed, but even if we happen to need other ViewHolder we can just write different implementation and ignore default item header and content provided by Property itself. 

**Tests:**

Helpers and Filters have small test written for them.
In case of AssetHelper testing is done with mockito as well. Context, AssetManager and InputStream are being mocked. InputStream is set to return fixed content upon any ::open() request.

License
----
MIT

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [Butterknife]: <http://jakewharton.github.io/butterknife/>
   [Timber]: <https://github.com/JakeWharton/timber>
   [Gson]: <https://github.com/google/gson>
   [RxJava]: <https://github.com/ReactiveX/RxJava>
   [Dagger]: <https://google.github.io/dagger/>
