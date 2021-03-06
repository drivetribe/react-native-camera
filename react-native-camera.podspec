require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name           = 'react-native-camera'
  s.version        = package['version']
  s.summary        = package['description']
  s.description    = package['description']
  s.license        = package['license']
  s.author         = package['author']
  s.homepage       = package['homepage']
  s.source         = { :git => 'https://github.com/react-native-community/react-native-camera', :tag => s.version }

  s.requires_arc   = true
  s.platform       = :ios, '8.0'

  s.subspec "RN" do |ss|
    ss.source_files = "ios/RN/**/*.{h,m}"
  end

  s.default_subspecs = "RN"

  s.preserve_paths = 'LICENSE', 'README.md', 'package.json', 'index.js'

  s.dependency 'React'
end
