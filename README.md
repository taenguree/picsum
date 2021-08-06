# picsum

- https://picsum.photos/ 사이트의 https://picsum.photos/v2/list?page=2&limit=100 를 이용하여 이미지를 그리드 형식으로 보여주는 프로젝트 입니다.
- 메인화면에서 그리드 형식으로 보여주고, 이미지를 클릭스 디테일 화면으로 이동되며 디테일화면서는 이미지를 크게 볼 수 있고, 흑백과 흐림처리를 할 수 있습니다.
- 메인화면에서 기본적으로 Glide 를 이용해 이미지를 로드합니다.
  - 이미지가 커서 여러 이미지를 로드하면 Glide 캐쉬 용량을 초과하기 때문에, 스크롤 다운 후에 다시 처음으로 스크롤 업할 경우 처음 이미지가 캐쉬에서 날아가 다시 네트웍을 통해 받아오는 현상을 하기 위해 Room 을 이용하여 한번 네트웍으로부터 받아온 이미지는 room 에 base64 인코딩 하여 저장합니다. 스크롤 업하여 처음 이미지로 돌아갈 경우 이 이미지가 Glide 캐쉬에서 삭제되었더라도, 룸에 인코딩하여 저장해두었기 때문에 이를 이용하여 로드하기 때문에 이미지가 뷰에 빠르게 보여집니다.

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
 
