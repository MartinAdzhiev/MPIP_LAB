package mk.ukim.finki.mpip.lab3.domain.movie.retrofit

import mk.ukim.finki.mpip.lab3.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class MovieDbApiProvider {

    companion object {
        @Volatile //kreirame statictki singleton objekt od MovieDbApi
        private var INSTANCE: MovieDbApi? = null

        @JvmStatic //ovaa oznaka znaci deka ako koristime java kod ovoj metod ke bide staticki
        fun getMovieDbApi(): MovieDbApi {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = createMovieDbApi()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        //interseptorot e objekt koj se koristi za manipulacija na request-ot i sekogas se koristi koga postojano se prakjaat nekoi parametri
        private fun createMovieDbApi(): MovieDbApi { //ovoj kod vo globala sekogas e ist
            class QueryParamInterceptor : Interceptor { //koga se definira interseptor
                @Throws(IOException::class) //interseptorot mora da ima override na intersept metodot
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request: Request = chain.request() //precekaj go request-ot
                    val htt = request.url.newBuilder()
                        .addQueryParameter("plot", "short" )
                        .addQueryParameter("apikey", BuildConfig.API_KEY) //pa dodadi mu parametri
                        .build() //treba da vnimavame koga postavuvame na git kod so otvoreno api key, vo toj slucaj samata baza moze da ni naplati
                    //i moze da ni go blokira api key-to ili da ne mozeme da ja postavime aplikacijata na google play
                    //poradi toa treba da se externalizira i da se postavi vo nesto vakvo kako local properties
                    request = request.newBuilder().url(htt).build()
                    return chain.proceed(request)
                }
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY //sekoja info sto doagja vo body mozeme da ja izlogirame

            val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(QueryParamInterceptor())
                .addInterceptor(httpLoggingInterceptor)//dodavame 2 interseptori, eden za logiranje dr za parametri
                .build() // fiksni se pri sekoj request
            val gson = GsonBuilder() //dodavame gson konverter za da mozeme da parsirame json
                .setLenient()
                .create()

            val gsonConverterFactory = GsonConverterFactory.create(gson)

            val retrofit = Retrofit.Builder()//kreirame retrofit objekt so base url i gson konverter
                .baseUrl("https://www.omdbapi.com")
                .client(okhttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
            return retrofit.create(MovieDbApi::class.java)
        }
    }

}