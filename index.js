/*
 * @flow
 */

import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
  DeviceEventEmitter, // android
  NativeAppEventEmitter, // ios
  NativeModules,
  Platform,
  StyleSheet,
  requireNativeComponent,
  View,
  ViewPropTypes,
} from 'react-native';

const CameraManager = NativeModules.CameraManager || NativeModules.CameraModule;

function convertNativeProps(props) {
  const newProps = { ...props };
  if (typeof props.aspect === 'string') {
    newProps.aspect = Camera.constants.Aspect[props.aspect];
  }

  if (typeof props.flashMode === 'string') {
    newProps.flashMode = Camera.constants.FlashMode[props.flashMode];
  }

  if (typeof props.orientation === 'string') {
    newProps.orientation = Camera.constants.Orientation[props.orientation];
  }

  if (typeof props.torchMode === 'string') {
    newProps.torchMode = Camera.constants.TorchMode[props.torchMode];
  }

  if (typeof props.type === 'string') {
    newProps.type = Camera.constants.Type[props.type];
  }

  if (typeof props.captureQuality === 'string') {
    newProps.captureQuality = Camera.constants.CaptureQuality[props.captureQuality];
  }

  if (typeof props.captureMode === 'string') {
    newProps.captureMode = Camera.constants.CaptureMode[props.captureMode];
  }

  return newProps;
}

type State = {
  isAuthorized: boolean,
  isRecording: boolean,
};

export default class Camera extends Component<*, State> {

  static constants = {
    Aspect: CameraManager.Aspect,
    Type: CameraManager.Type,
    CaptureMode: CameraManager.CaptureMode,
    CaptureTarget: CameraManager.CaptureTarget,
    CaptureQuality: CameraManager.CaptureQuality,
    Orientation: CameraManager.Orientation,
    FlashMode: CameraManager.FlashMode,
    TorchMode: CameraManager.TorchMode
  };

  static propTypes = {
    ...ViewPropTypes,
    aspect: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number
    ]),
    captureAudio: PropTypes.bool,
    captureMode: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number
    ]),
    captureQuality: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number
    ]),
    captureTarget: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number
    ]),
    defaultOnFocusComponent: PropTypes.bool,
    flashMode: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number
    ]),
    keepAwake: PropTypes.bool,
    onFocusChanged: PropTypes.func,
    onZoomChanged: PropTypes.func,
    mirrorImage: PropTypes.bool,
    onSessionDidStartRunning: PropTypes.func,
    orientation: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number
    ]),
    playSoundOnCapture: PropTypes.bool,
    torchMode: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number
    ]),
    type: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number
    ])
  };

  static defaultProps = {
    aspect: CameraManager.Aspect.fill,
    type: CameraManager.Type.back,
    orientation: CameraManager.Orientation.auto,
    captureAudio: false,
    captureMode: CameraManager.CaptureMode.still,
    captureTarget: CameraManager.CaptureTarget.cameraRoll,
    captureQuality: CameraManager.CaptureQuality.high,
    defaultOnFocusComponent: true,
    flashMode: CameraManager.FlashMode.off,
    playSoundOnCapture: true,
    torchMode: CameraManager.TorchMode.off,
    mirrorImage: false,
  };

  state: State = {
    isAuthorized: false,
    isRecording: false
  };

  static checkDeviceAuthorizationStatus = CameraManager.checkDeviceAuthorizationStatus;
  static checkVideoAuthorizationStatus = CameraManager.checkVideoAuthorizationStatus;
  static checkAudioAuthorizationStatus = CameraManager.checkAudioAuthorizationStatus;

  cameraRef: any;

  setNativeProps(props: Object) {
    this.cameraRef.setNativeProps(props);
  }

  componentWillMount() {

    let { captureMode } = convertNativeProps({ captureMode: this.props.captureMode })
    let hasVideoAndAudio = this.props.captureAudio && captureMode === Camera.constants.CaptureMode.video
    let check = hasVideoAndAudio ? Camera.checkDeviceAuthorizationStatus : Camera.checkVideoAuthorizationStatus;

    if (check) {
      check().then((isAuthorized) => {
        this.setState({ isAuthorized });
      })
    }
  }

  componentWillUnmount() {
    if (this.state.isRecording) {
      this.stopCapture();
    }
  }

  render() {
    const nativeProps = convertNativeProps(this.props);

    return <RCTCamera ref={(ref) => { this.cameraRef = ref; }} {...nativeProps} style={this.props.style} />;
  }

  capture(options?: Object) {
    const props = convertNativeProps(this.props);
    const newOptions = {
      audio: props.captureAudio,
      mode: props.captureMode,
      playSoundOnCapture: props.playSoundOnCapture,
      target: props.captureTarget,
      quality: props.captureQuality,
      type: props.type,
      title: '',
      description: '',
      mirrorImage: props.mirrorImage,
      ...options
    };

    if (newOptions.mode === Camera.constants.CaptureMode.video) {
      newOptions.totalSeconds = (newOptions.totalSeconds > -1 ? newOptions.totalSeconds : -1);
      newOptions.preferredTimeScale = newOptions.preferredTimeScale || 30;
      this.setState({ isRecording: true });
    }

    return CameraManager.capture(newOptions);
  }

  stopCapture() {
    if (this.state.isRecording) {
      this.setState({ isRecording: false });
      return CameraManager.stopCapture();
    }
    // $FlowFixMe - Flow bug
    return Promise.reject('Not Recording');
  }

  getFOV() {
    return CameraManager.getFOV();
  }

  hasFlash() {
    if (Platform.OS === 'android') {
      const props = convertNativeProps(this.props);
      return CameraManager.hasFlash({
        type: props.type
      });
    }
    return CameraManager.hasFlash();
  }
}

export const constants = Camera.constants;

const RCTCamera = requireNativeComponent('RCTCamera', Camera);
