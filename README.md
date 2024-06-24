<h1 align="start">CoinMarketCap Application :chart_with_upwards_trend::moneybag:</h1>


<p align="start">  
🗡️  CoinMarketCap app demonstrates modern Android development with Hilt, Flow and ViewModel based on MVVM architecture. 
  <br>
  <br>
🗡️  API: https://coinmarketcap.com/api/documentation/v1/
</p>
<br>


## Tech stack & Open-source libraries

<div style="float: right; margin-left: 400px;">
  <img src="/previews/coinsPreview.gif" align="right" width="300"/>
</div>

- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - Paging 3: Helps you load and display pages of data from a larger dataset from local storage or over a network.
  - [Hilt](https://dagger.dev/hilt/):  for dependency injection.
- Architecture
  -  MVVM Architecture
  -  Clean Architecture
- Retrofit


## Architecture
**CoinMarketCap** app is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

