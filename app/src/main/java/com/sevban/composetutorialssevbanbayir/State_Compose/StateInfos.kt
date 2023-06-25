package com.sevban.composetutorialssevbanbayir.State_Compose

/**
 * How can an App lose ui state ?
 * THREE IMPORTANT CASE:
 *  1- Configuration changes: (Causes activity recreation*)

 *
 *      - Screen rotates
 *      - Entering & leaving multi-window mode
 *      - Screen resizing
 *      - Switching to Light or Dark mode
 *      - When the system is low on resources and your app
 *        on the background. System may destroy your app while
 *        the user is interacting with other apps
 *      - The user or system may abruptly destroy your app
 *      - Keyboard availability
 *      **The solution is using ViewModel API.
 *      *  The system recreates an Activity when a configuration change occurs.
 *    To do this, the system calls onDestroy() and destroys the existing Activity instance.
 *    It then creates a new instance using onCreate(), and this new Activity instance is
 *    initialized with the new, updated configuration.This also means that the system also recreates the UI with the new configuration.
 *
 *  2- Abrupt Dissmissals
 *      The solution is using disk (with Room or DataStore).
 *  3- System Needs Resource
 *      The solution is using savedStateHandle or saveable APIs.
 * */


/**
 *      For unexpected dismissals we can not use memory, because it is cleaned
 *      when dismissal is occured. Instead of memory we must persist that data
 *      in the disk. (Persisting on the network is also an option)
 *
 *      For persisting data in disk there two APIs available in Jetpack: DataStore for
 *      simple variables and small datasets; Room for complex datasets.
 *
 *      Using disk has some drawbacks:
 *          - Limited by disk space
 *          - Slow reads/writes
 *           (That's why it would not make sense
 *           storing UI state in datastore)
 *          -
 * */


/**
 *      For the case that system is in a need of resources and so it
 *      destroys your app, Android has Saved State APIs.
 *
 *      Drawbacks:
 *          - Limited by Bundle(We can store only essential data)
 *          - X More than 50KB.
 *          - Slow reads/writes due to serialize-deserialization
 *          - Usually for transient state: Scroll position of a list,
 *          ID of the item in detail screen, input in textfields
 *
 *          JETPACK COMPOSE : rememberSaveable
 *                                                  ---> These APIs make sense when desired state
 *                                                       is part of the UI logic.
 *          View System: onSaveInstanceState
 *
 *          When desired state is part of business logic then you have to use SavedStateHandle API.
 *          But keep in mind that SavedStateHandle API only stores data written to it when Activity
 *          is stopped. (goo.gle/architecture-viewmode-savedstate)
 *
 *
 *
 * */