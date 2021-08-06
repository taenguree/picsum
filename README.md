# picsum

## Architecture

- 뷰들이 컴포넌트화 될 수 있도록 커스텀하게 구성한 아키텍쳐를 사용합니다. 
- 상태 옵져빙을 통한 뷰 업데이트가 아닌 명령형 방식의 뷰 업데이트를 사용합니다.

![architecture](/architecture/actitecture.png)

## Languages

- App : Kotlin
- Build : Gradle with groovy

## Libraries

The internal libraries used include:

- Stroke recognition
  - [Myscript](https://github.com/Knowre-Dev/myscript-android)
  
The external libraries used include:

- Basic
  - [Coroutine](https://kotlinlang.org/docs/coroutines-overview.html)

- Network
  - [Retrofit](http://square.github.io/retrofit/)
  - [OkHttp](https://github.com/square/okhttp)
  
- Injection
  - [Hilt](https://github.com/google/dagger)

- UI
  - [Glide](https://github.com/bumptech/glide)
 
