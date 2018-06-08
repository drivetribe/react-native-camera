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