# Jetpack Compose Basics

This repository contains the completed [introductory lesson](https://developer.android.com/jetpack/compose/tutorial?authuser=1) 
on the basics of Jetpack Compose.

The rest of this README.md file contains the notes I took while going through the tutorial. Some
are verbatim copy and paste, while most are written in my own words to solidify my 
understanding of the different concepts.

## Table of Contents
* [What is Jetpack Compose](#what-is-Jetpack-Compose?)
* [Foreword](#foreword)
* [Lesson 1: Composable functions](#Lesson-1:-Composable-functions)
* [Lesson 2: Layouts](#Lesson-2:-Layouts)
* [Lesson 3: Material Design](#Lesson-3:-Material-Design)

## What is Jetpack Compose?

Jetpack Compose is a modern toolkit for building **native Android UI.** It simplifies and accelerates 
UI development on Android with less code, powerful tools, and **intuitive Kotlin APIs.**

With Jetpack Compose, the **UI is built with a series of declarative functions**.

## Foreword

At the heart of the Jetpack Compose library are composable functions. The compose compiler 
recognizes those functions thanks to the `@Composable` annotation.

Composables let you define your app's UI programmatically by **describing its shape and data 
dependencies**, rather than focusing on the process of the UI's construction

> Think data first, state next, and appearance last.

In this tutorial, you'll build a simple UI component with declarative functions.

You won't be editing any XML layouts or directly creating the UI widgets.

Instead, you will call Jetpack Compose functions to say what elements you want, and the Compose 
compiler will do the rest.

## Lesson 1: Composable functions

Jetpack Compose is designed to make an integral use of composable functions. These functions 
encapsulates the state and data that belongs to a specific UI component.

This process focuses on a declarative approach to building the UI by coupling data, state, 
and appearance into small reusable chunks of composable functions.

As of the beta version of Jetpack Compose, and Android Arctic Fox, it is possible to create a new 
kind of project: Empty Compose Activity.

As it's name suggests, this will create an Android project will all the required Compose 
dependencies.

In Android development so far, we have used the `setContent` method within `onCreate()` to inflate 
an XML layout. That inflated layout used to be the blueprint for how the UI should look.

With compose, a paradigm shift is necessary; we now use `setContent` to tell Android the UI 
required for our data instead. The difference may seem subtle but, as we will see, 
the ramifications are wide.

Jetpack Compose uses a custom Kotlin compiler plugin to transform these composable functions within 
the `setContent` block into the app's UI elements

### Define a composable function
Composable functions can only be called from within the scope of other composable functions. This 
is somewhat akin to coroutines where a child coroutine inherits the scope of its parent coroutine.

To make a function composable, add the @Composable annotation.

### Preview your composable in Android Studio

Android Studio Arctic Fox allows you to preview your composables within the window of the 
`MainActivity`'s code in a near identical fashion to that of the layout preview window.

## Lesson 2: Layouts

UI elements are hierarchical, with elements contained in other elements. In Compose, you build a UI 
hierarchy by calling composable functions from other composable functions.

> It's a best practice to create separate preview functions that aren't called by the app; having 
> dedicated preview functions improves performance, and also makes it easier to set up multiple 
> previews later on

When arranging composables, layouts serve the same purpose as they do in XML layouts, i.e. measure 
and place children layouts according to parent layout constraints.

Column is one such layout. It lets you stack elements vertically, one on top of each other.

The default settings stack all the children directly, one after another, with no spacing. **The 
column itself is put in the content view's top left corner.**

### Add style settings to the column

It is possible to pass parameters to composables. For instance, we control positioning of the 
Column layout's childrenviews via what is aptly called a Modifier.

```kotlin
@Composable
fun NewsStory() {
    Column(
	modifier = Modifier.padding(16.dp)
    ) {
	// Children will be spaced away from the Column layout by
	// 16dp.
	...
    }
}
```

By passing parameters to the Column call, you can configure the column's size and position with 
respect to its surrounding view, i.e. its parent UI in the hierarchy.

> Do not confuse padding with margin. Padding occurs within the view and hence affects children 
> views, while margin occurs outside of the and only potentially affect children views.

### Add a picture

```kotlin
@Composable
fun NewsStory() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.header),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
	 // A spacer is added to seperate the header from the graphic
        Spacer(Modifier.height(16.dp))

        Text("A day in Shark Fin Cove")
        Text("Davenport, California")
        Text("December 2018")
    }
}
```
The painter parameter uses `painterResource` to determine the source of the `drawable`.

## Lesson 3: Material Design

Compose is built to support **material design principles.**

### Apply a shape

> One of the pillars of the Material Design System is Shape

Use the `clip()` function to round the corners of the image.

```kotlin
@Composable
fun NewsStory() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.header),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(16.dp))

            Text("A day in Shark Fin Cove")
            Text("Davenport, California")
            Text("December 2018")
        }
    }
}
```

### Style the text

Compose makes it easy to take advantage of Material Design principles. Apply `MaterialTheme` to the 
components you've created.

```kotlin
@Composable
fun NewsStory() {
    MaterialTheme {
        val typography = MaterialTheme.typography
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.header),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(16.dp))

            Text(
                "A day wandering through the sandhills " +
                     "in Shark Fin Cove, and a few of the " +
                     "sights I saw",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Text("Davenport, California",
                style = typography.body2)
            Text("December 2018",
                style = typography.body2)
        }
    }
}
```