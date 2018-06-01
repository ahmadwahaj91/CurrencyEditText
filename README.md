# CurrencyEditText
Currency Edit Text is an Android View component that allows the user to enter numerical value , it inserts commas to the numerical value entered and a Currency Sign is also concatenated with the numerical value entered. It is an animated custom built expandable view that expands and contracts with respect to the value entered in it.

### How to Use (Usage Sample)

A step by step approach to guide you through, how to use the library

do this in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency:

```
dependencies {
    compile 'com.github.ahmadwahaj91:CurrencyEditText:0.1.0'
}
```

End with an example of getting some data out of the system or using it for a little demo

Define a view in your layout file:

```
 <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    tools:context="com.db.currencyedittext.MainActivity">

    <customlibraries.db.com.currencyedittext.CurrencyEditText
        android:id="@+id/price"
        android:layout_width="wrap_content"
        android:layout_height="40dp"
        android:layout_gravity="center"
        android:inputType="number"
        android:maxLength="10"
        app:currency_symbol="@string/pound"
        app:show_commas="true"
        app:show_currency="true" />

</LinearLayout>

```

## Authors

* **Ahmad Wahaj** - *Initial work* - [Ahmad Wahaj](https://github.com/ahmadwahaj91)


