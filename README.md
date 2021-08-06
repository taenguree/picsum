# picsum

## Architecture

- Redux 구조를 사용합니다.
- 뷰들이 컴포넌트화 될 수 있도록 커스텀하게 구성한 아키텍쳐를 사용합니다. 
- 상태 옵져빙을 통한 뷰 업데이트가 아닌 명령형 방식의 뷰 업데이트를 사용합니다.

![architecture](/architecture/architecture.png)

- StateModel : Redux 구조에 기반하여 뷰와 데이터 사이의 비지니스 로직을 담당하는 역할을 합니다.
- Renderer : 뷰와 StateModel 사이에 coroutine 서포트를 위한 클래스 입니다.

## Languages

- App : Kotlin
- Build : Gradle with groovy

## Libraries

- Basic
  - [Coroutine](https://kotlinlang.org/docs/coroutines-overview.html)

- Network
  - [Retrofit](http://square.github.io/retrofit/)
  - [OkHttp](https://github.com/square/okhttp)
  
- Injection
  - [Hilt](https://github.com/google/dagger)

- UI
  - [Glide](https://github.com/bumptech/glide)
 
