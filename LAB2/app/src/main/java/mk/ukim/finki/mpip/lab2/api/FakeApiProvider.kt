package mk.ukim.finki.mpip.lab2.api

import mk.ukim.finki.mpip.lab2.model.Movie

class FakeApiProvider {

    companion object {
        @Volatile
        private var INSTANCE: FakeApi? = null

        @JvmStatic
        fun getFakeApi(): FakeApi {
            return INSTANCE ?: synchronized(this) {
                val instance = createFakeApi()
                INSTANCE = instance
                instance
            }
        }

        private fun createFakeApi(): FakeApi {
            val fakeApi = FakeApi()

            fakeApi.add(
                Movie(
                    1,
                    "Titanic",
                    "Ship sinking",
                    "James Cameron",
                    listOf("Leonardo Di Caprio", "Kate Winslet")
                )
            )

            fakeApi.add(
                Movie(
                    2,
                    "Furious 7",
                    "Driving crazy",
                    "James Wan",
                    listOf("Vin Diesel", "Jason Statham")
                )
            )

            fakeApi.add(
                Movie(
                    3,
                    "Rocky",
                    "Boxing",
                    "John G. Avildsen",
                    listOf("Sylvester Stallone", "Dolph Lundgren")
                )
            )

            fakeApi.add(
                Movie(
                    4,
                    "Oppenheimer",
                    "Nuclear bomb",
                    "Christopher Nolan",
                    listOf("Cillian Murphy", "Florence Pugh")
                )
            )

            return fakeApi
        }
    }
}