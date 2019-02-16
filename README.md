# AutoCompleteEmailView
Custom view for email autocomplete suggestions

<img src="gif/demo.gif" width="256">

## Setup
```
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
    implementation 'com.github.oguzcelik:AutoCompleteEmailView:1.0.0'
}
```

## Usage

```xml
<com.oguzce.emailautocompleteedittext.EmailAutoCompleteEditText
            android:id="@+id/autoCompleteEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textColor="@color/colorNormalText"
            app:inputColor="@color/colorNormalText"
            app:suggestionColor="@color/colorHintText" />
```
```kotlin
val domainList = listOf("@hotmail.com", "@gmail.com")

autoCompleteEditText.startSuggestionTextWatcher(domainList)

-------- or --------

autoCompleteEditText.setSuggestionList(domainList)
autoCompleteEditText.startSuggestionTextWatcher()

...

autoCompleteEditText.stopSuggestionTextWatcher()

```

# License
    Copyright 2019 oguzcelik

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.