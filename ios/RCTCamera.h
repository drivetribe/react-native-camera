#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import "CameraFocusSquare.h"
#import "RCTComponent.h"

@class RCTCameraManager;

@interface RCTCamera : UIView

@property (nonatomic, copy) RCTBubblingEventBlock onSessionDidStartRunning;

- (id)initWithManager:(RCTCameraManager*)manager bridge:(RCTBridge *)bridge;

@end
