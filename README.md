# React Native Camera [DriveTribe fork]

The comprehensive camera module for React Native. Including photographs, videos. Same as https://github.com/react-native-community/react-native-camera but without the barcode & face detection.

`import { RNCamera } from 'react-native-camera';`

#### How to use master branch?
Inside your package.json, use this
`"react-native-camera": "git+https://git@github.com/react-native-community/react-native-camera"`
instead of `"react-native-camera": "^1.0.0"`.

##### Permissions
To use the camera on Android you must ask for camera permission:
```java
  <uses-permission android:name="android.permission.CAMERA" />
```
To enable `video recording` feature you have to add the following code to the `AndroidManifest.xml`:
```java
  <uses-permission android:name="android.permission.RECORD_AUDIO"/>
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

![5j2jduk](https://cloud.githubusercontent.com/assets/2302315/22190752/6bc6ccd0-e0da-11e6-8e2f-6f22a3567a57.gif)

## Migrating from RCTCamera to RNCamera

See this [doc](./docs/migration.md)

### RNCamera Docs
[RNCamera](./docs/RNCamera.md)

### Docs old RCTCamera
[RCTCamera](./docs/RCTCamera.md)

## Getting started

### Requirements
1. JDK >= 1.7 (if you run on 1.6 you will get an error on "_cameras = new HashMap<>();")
2. With iOS 10 and higher you need to add the "Privacy - Camera Usage Description" key to the Info.plist of your project. This should be found in 'your_project/ios/your_project/Info.plist'. Add the following code:
```
<key>NSCameraUsageDescription</key>
<string>Your message to user when the camera is accessed for the first time</string>

<!-- Include this only if you are planning to use the camera roll -->
<key>NSPhotoLibraryUsageDescription</key>
<string>Your message to user when the photo library is accessed for the first time</string>

<!-- Include this only if you are planning to use the microphone for video recording -->
<key>NSMicrophoneUsageDescription</key>
<string>Your message to user when the microphone is accessed for the first time</string>
```
3. On Android, you require `buildToolsVersion` of `25.0.2+`. _This should easily and automatically be downloaded by Android Studio's SDK Manager._

4. On iOS 11 and later you need to add `NSPhotoLibraryAddUsageDescription` key to the Info.plist. This key lets you describe the reason your app seeks write-only access to the user’s photo library. Info.plist can be found in 'your_project/ios/your_project/Info.plist'. Add the following code:
```
<!-- Include this only if you are planning to use the camera roll -->
<key>NSPhotoLibraryAddUsageDescription</key>
<string>Your message to user when the photo library is accessed for the first time</string>
```

### Mostly automatic install with react-native
1. `npm install react-native-camera --save`
3. `react-native link react-native-camera`

### Mostly automatic install with CocoaPods
1. `npm install react-native-camera --save`
2. Add the plugin dependency to your Podfile, pointing at the path where NPM installed it:
```obj-c
pod 'react-native-camera', path: '../node_modules/react-native-camera'
```
3. Run `pod install`

### Manual install
#### iOS
1. `npm install react-native-camera --save`
2. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
3. Go to `node_modules` ➜ `react-native-camera` and add `RNCamera.xcodeproj`
4. Expand the `RNCamera.xcodeproj` ➜ `Products` folder
5. In XCode, in the project navigator, select your project. Add `libRNCamera.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
6. Click `RNCamera.xcodeproj` in the project navigator and go the `Build Settings` tab. Make sure 'All' is toggled on (instead of 'Basic'). In the `Search Paths` section, look for `Header Search Paths` and make sure it contains both `$(SRCROOT)/../../react-native/React` and `$(SRCROOT)/../../../React` - mark both as `recursive`.

## Usage

### RNCamera

Take a look into this [documentation](./docs/RNCamera.md).

### RCTCamera

Since `1.0.0`, RCTCamera is deprecated, but if you want to use it, you can see its [documentation](./docs/RCTCamera.md).

## Open Collective
We are just beginning a funding campaign for react-native-camera. Contributions are greatly appreciated. When we gain more than $250 we will begin distributing funds to core maintainers in a fully transparent manner. Feedback for this process is welcomed, we will continue to evolve the strategy as we grow and learn more.

### Backers

Support us with a monthly donation and help us continue our activities. [[Become a backer](https://opencollective.com/react-native-camera#backer)]

<a href="https://opencollective.com/react-native-camera/backer/0/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/0/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/1/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/1/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/2/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/2/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/3/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/3/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/4/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/4/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/5/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/5/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/6/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/6/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/7/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/7/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/8/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/8/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/9/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/9/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/10/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/10/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/11/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/11/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/12/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/12/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/13/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/13/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/14/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/14/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/15/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/15/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/16/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/16/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/17/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/17/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/18/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/18/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/19/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/19/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/20/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/20/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/21/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/21/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/22/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/22/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/23/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/23/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/24/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/24/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/25/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/25/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/26/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/26/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/27/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/27/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/28/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/28/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/backer/29/website" target="_blank"><img src="https://opencollective.com/react-native-camera/backer/29/avatar.svg"></a>


### Sponsors

Become a sponsor and get your logo on our README on Github with a link to your site. [[Become a sponsor](https://opencollective.com/react-native-camera#sponsor)]

<a href="https://opencollective.com/react-native-camera/sponsor/0/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/0/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/1/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/1/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/2/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/2/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/3/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/3/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/4/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/4/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/5/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/5/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/6/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/6/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/7/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/7/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/8/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/8/avatar.svg"></a>
<a href="https://opencollective.com/react-native-camera/sponsor/9/website" target="_blank"><img src="https://opencollective.com/react-native-camera/sponsor/9/avatar.svg"></a>


------------

Thanks to Brent Vatne (@brentvatne) for the `react-native-video` module which provided me with a great example of how to set up this module.
