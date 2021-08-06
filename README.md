# picsum

- https://picsum.photos/ 사이트의 https://picsum.photos/v2/list?page=2&limit=100 를 이용하여 이미지를 그리드 형식으로 보여주는 프로젝트 입니다.
- 메인화면에서 그리드 형식으로 보여주고, 이미지를 클릭스 디테일 화면으로 이동되며 디테일화면서는 이미지를 크게 볼 수 있고, 흑백과 흐림처리를 할 수 있습니다.
- 메인화면에서 기본적으로 Glide 를 이용해 이미지를 로드합니다.

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

- Data
  - [Room](https://developer.android.com/training/data-storage/room)

- UI
  - [Glide](https://github.com/bumptech/glide)
 
