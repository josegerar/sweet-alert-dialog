Sweet Alert Dialog
===================
SweetAlert for Android, a beautiful and clever alert dialog

#### This is a fork of [F0RIS/sweet-alert-dialog](https://github.com/F0RIS/sweet-alert-dialog) with additional fixes and features.
**Improvements:**
- **Builder Pattern:** New way to initialize and configure dialogs more cleanly.
- **Adaptive Content Layout:** Short messages use their natural height. Long text, inputs, and custom views scroll only inside the content area while the icon, title, and action buttons remain visible.
- **Responsive Action Buttons:** One, two, or three buttons are distributed with a constrained horizontal chain so the last action is never clipped.
- **Modern Android Compatibility:** Uses AndroidX resource and HTML compatibility APIs, native pressed states, improved accessibility, RTL-aware spacing, and API 36 compatible dependencies.
- **Automatic Dark Mode:** Ability to automatically detect and apply dark mode based on system settings.
- **Toast Mode:** Small, non-blocking notifications that auto-dismiss.
- **Native Input Support:** Built-in support for text, passwords, and more without custom views.
- **Auto-close Timer:** Set a timer to automatically dismiss the dialog.
- **Positioning (Gravity):** Display dialogs at the top, bottom, or center of the screen.
- **Queue System:** Chain multiple dialogs to appear one after another.
- **Footer Support:** Add a footer area for secondary information.
- Ability to set custom view
- More convenient interface to bind listeners (like in AlertDialog)
- Third neutral button with own listener, colors, methods and etc.
- Ability to disable buttons
- Ability to set buttons stroke width
- Dark style of dialogs
- Ability to make dialogs without buttons
- Support of HTML tags
- Ability to set text size
- Ability to set buttons color

Some screenshots of the new features:

<img src="https://cloud.githubusercontent.com/assets/10178982/24260517/c6f72da6-0ffc-11e7-9a16-67fea4010a34.jpg" width="30%"/>

<img src="https://user-images.githubusercontent.com/10178982/59605653-eee87d80-9117-11e9-9421-b116536c9388.png" width="30%"/>

#### Known issues:
None currently reported for this fork.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Sweet%20Alert%20Dialog-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1065)

[中文版](https://github.com/pedant/sweet-alert-dialog/blob/master/README.zh.md)

Inspired by JavaScript [SweetAlert](http://tristanedwards.me/sweetalert)

[Demo Download](https://github.com/F0RIS/sweet-alert-dialog/releases/download/1.5.3/sample-v1.5.3.apk)

## ScreenShot
![image](https://github.com/pedant/sweet-alert-dialog/raw/master/change_type.gif)

## Setup
The simplest way to use SweetAlertDialog is to add the library as aar dependency to your build.

**Maven**

    <dependency>
      <groupId>com.github.josegerar</groupId>
      <artifactId>sweet-alert-dialog</artifactId>
      <version>2.1.1</version>
      <type>aar</type>
    </dependency>

**Gradle**

    repositories {
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        implementation 'com.github.josegerar:sweet-alert-dialog:2.1.1'
    }

### Version 2.1.1

- Keeps the title, icon, footer, and action buttons visible when content is taller than the screen.
- Scrolls only the message, input, or custom-view area when necessary; short messages do not show an unnecessary scrollbar.
- Uses a responsive `ConstraintLayout` action row for one, two, or three buttons.
- Updates AndroidX compatibility, HTML/resource loading, pressed states, accessibility, and RTL spacing.
- Includes a long-text sample with three actions for layout testing.

The library supports Android API 19 and newer and is built with Android API 36.

## Usage

show material progress

    SweetAlertDialog pDialog = new SweetAlertDialog.Builder(this, SweetAlertDialog.PROGRESS_TYPE)
        .setTitleText("Loading")
        .setCancelable(false)
        .build();
    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
    pDialog.show();

![image](https://github.com/pedant/sweet-alert-dialog/raw/master/play_progress.gif)

You can customize progress bar dynamically with materialish-progress methods via **SweetAlertDialog.getProgressHelper()**:
- resetCount()
- isSpinning()
- spin()
- stopSpinning()
- getProgress()
- setProgress(float progress)
- setInstantProgress(float progress)
- getCircleRadius()
- setCircleRadius(int circleRadius)
- getBarWidth()
- setBarWidth(int barWidth)
- getBarColor()
- setBarColor(int barColor)
- getRimWidth()
- setRimWidth(int rimWidth)
- getRimColor()
- setRimColor(int rimColor)
- getSpinSpeed()
- setSpinSpeed(float spinSpeed)

thanks to the project [materialish-progress](https://github.com/pnikosis/materialish-progress) and [@croccio](https://github.com/croccio) participation.

more usages about progress, please see the sample.

A basic message：

    new SweetAlertDialog.Builder(this)
        .setTitleText("Here's a message!")
        .show();

A title with a text under：

    new SweetAlertDialog.Builder(this)
        .setTitleText("Here's a message!")
        .setContentText("It's pretty, isn't it?")
        .show();

Long content is handled automatically. No extra scroll configuration is required:

    new SweetAlertDialog.Builder(this, SweetAlertDialog.WARNING_TYPE)
        .setTitleText("Terms and conditions")
        .setContentText(longText)
        .setCancelText("Cancel")
        .setNeutralText("Later")
        .setConfirmText("Accept")
        .show();

When `longText` exceeds the available height, only the content area scrolls and all three buttons remain visible.

A error message：

    new SweetAlertDialog.Builder(this, SweetAlertDialog.ERROR_TYPE)
        .setTitleText("Oops...")
        .setContentText("Something went wrong!")
        .show();

A warning message：

    new SweetAlertDialog.Builder(this, SweetAlertDialog.WARNING_TYPE)
        .setTitleText("Are you sure?")
        .setContentText("Won't be able to recover this file!")
        .setConfirmText("Yes,delete it!")
        .show();

A success message：

    new SweetAlertDialog.Builder(this, SweetAlertDialog.SUCCESS_TYPE)
        .setTitleText("Good job!")
        .setContentText("You clicked the button!")
        .show();

A message with a custom icon：

    new SweetAlertDialog.Builder(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
        .setTitleText("Sweet!")
        .setContentText("Here's a custom image.")
        .setCustomImage(R.drawable.custom_img)
        .show();

A message with a custom view：

    final EditText editText = new EditText(this);
    new SweetAlertDialog.Builder(this, SweetAlertDialog.NORMAL_TYPE)
            .setTitleText("Custom view")
            .setConfirmText("Ok")
            .setCustomView(editText)
            .show();


Different ways to bind the listener to button：

    new SweetAlertDialog.Builder(this, SweetAlertDialog.WARNING_TYPE)
        .setTitleText("Are you sure?")
        .setContentText("Won't be able to recover this file!")
        .setConfirmButton("Yes,delete it!", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        })
        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        })
        .show();


Disable button

    final SweetAlertDialog disabledBtnDialog = new SweetAlertDialog.Builder(this, SweetAlertDialog.NORMAL_TYPE)
            .setTitleText("Title")
            .setContentText("Disabled button dialog")
            .setConfirmText("Confirm")
            .setCancelText("Cancel")
            .show();

    disabledBtnDialog.setOnShowListener(new DialogInterface.OnShowListener() {
        @Override
        public void onShow(DialogInterface dialog) {
            disabledBtnDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setEnabled(false);
        }
    });
    disabledBtnDialog.show();


**Change** the dialog style upon confirming：

    new SweetAlertDialog.Builder(this, SweetAlertDialog.WARNING_TYPE)
        .setTitleText("Are you sure?")
        .setContentText("Won't be able to recover this file!")
        .setConfirmButton("Yes,delete it!", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog
                    .setTitleText("Deleted!")
                    .setContentText("Your imaginary file has been deleted!")
                    .setConfirmText("OK")
                    .setConfirmClickListener(null)
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }
        })
        .show();

More usages about dark mode:

    // Automatic dark mode (enabled by default in Builder)
    new SweetAlertDialog.Builder(this)
        .setTitleText("Auto Dark Mode")
        .show();

    // Force Dark Mode
    new SweetAlertDialog.Builder(this)
        .setTitleText("Forced Dark")
        .setDarkStyle(true)
        .show();

    // Force Light Mode (even if system is in dark mode)
    new SweetAlertDialog.Builder(this)
        .setTitleText("Forced Light")
        .setAutoDarkMode(false)
        .show();

### Additional features

Toast Mode (Non-blocking):

    new SweetAlertDialog.Builder(this, SweetAlertDialog.SUCCESS_TYPE)
        .setTitleText("Saved!")
        .setToast(true)
        .setTimer(2000)
        .show();

Custom Positioning (Gravity):

    // Display at the top of the screen
    new SweetAlertDialog.Builder(this)
        .setTitleText("Top Alert")
        .setGravity(Gravity.TOP)
        .show();

    // Display at the bottom of the screen
    new SweetAlertDialog.Builder(this)
        .setTitleText("Bottom Alert")
        .setGravity(Gravity.BOTTOM)
        .show();

Native Input:

    new SweetAlertDialog.Builder(this)
        .setTitleText("Enter your name")
        .setInputType(InputType.TYPE_CLASS_TEXT)
        .setInputPlaceholder("John Doe")
        .setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                String name = sDialog.getInputText();
                sDialog.dismissWithAnimation();
            }
        })
        .show();

Custom Positioning (Gravity):

    new SweetAlertDialog.Builder(this)
        .setTitleText("Top Alert")
        .setGravity(Gravity.TOP)
        .show();

Footer Information:

    new SweetAlertDialog.Builder(this)
        .setTitleText("Title")
        .setContentText("Main content")
        .setFooterText("Copyright 2024")
        .show();

Queueing Dialogs:

    new SweetAlertQueue()
        .add(new SweetAlertDialog.Builder(this).setTitleText("Step 1"))
        .add(new SweetAlertDialog.Builder(this).setTitleText("Step 2"))
        .add(new SweetAlertDialog.Builder(this).setTitleText("Step 3"))
        .showNext();

[more android tech shares: pedant.cn](http://www.pedant.cn)

## License

    The MIT License (MIT)

    Copyright (c) 2014 Pedant(http://pedant.cn)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


