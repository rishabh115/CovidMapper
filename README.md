# CovidMapper
A minimal library for getting Covid-19 data of all the countries fetched from [worldmeters.info](https://www.worldometers.info/coronavirus/). 

<img src="https://github.com/rishabh115/CovidMapper/blob/master/screenshots/Screenshot_1585083277.png" width="200" height="400">

## Instructions

_Follow these instructions to integrate covidmapper library in your project_

1. Open your project in Android Studio
2. Download the library (using Git, or a zip archive to unzip).
3. Copy and paste the covidmapper folder to your project top-level directory.
4. Make sure the library is listed at the top of your settings.gradle file by adding the following line
    ```
    include ':app'
    include ':covidmapper'
    ```
5. Open the app module's build.gradle file and add a new line to the dependencies block as shown in the following snippet:
   ```
    dependencies {
      implementation project(path: ':covidmapper')
    }
   ```
6. Add Internet permission in the `AndroidManifest.xml`.    
7. Click <b>Sync Project with Gradle Files</b>.   

## Usage

 `DataParser` is the main class responsible for making the request, parsing the data and passing it down to the listener under the hood. So, you don't have write any such networking and parsing logic by yourself. To get the data in any component, just implement `DataListener` interface and call `DataParser(this).getCountriesData()` as soon as the views are ready.
 
 Here is a simple example of using this library:
 ```kotlin
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import android.widget.ProgressBar
    import android.widget.Toast
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView

    class MainActivity : AppCompatActivity(), DataListener {

        private lateinit var someAdapter: SomeAdapter
        private lateinit var progress: ProgressBar

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val someList = findViewById<RecyclerView>(R.id.rv_main)
            progress = findViewById(R.id.progress)
            someAdapter = SomeAdapter()
            someList.adapter = someAdapter
            someList.layoutManager = LinearLayoutManager(this)
            progress.visibility = View.VISIBLE
            
            // Just add this line and see the magic
            DataParser(this).getCountriesData()
        }

        override fun onDataReceived(response: Response) {
            when(response){
                is Success -> {
                    progress.visibility = View.GONE
                    someAdapter.setData(response.data)
                }
                is Failure -> {
                    progress.visibility = View.GONE
                    Toast.makeText(applicationContext, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
                }
            }.exhaustiveCall
        }
      }

 ```
 
 `CountryData.kt`
 
 ```kotlin
    data class CountryData (val country: String, val totalCases: Int, val newCases: Int,
                        val totalDeaths: Int, val newDeaths: Int, val totalRecovered: Int,
                        val activeCases:Int, val criticalCases:Int)
 ```
 
 `Response` is a sealed class provided in the library for streamlined processing of Success or Failure situation at one place. When network request is successful, the listener is provided with a Success object that includes list of `CountryData`.
 
 ```kotlin
    sealed class Response
    class Success(val data: List<CountryData>):Response()
    class Failure: Response()
 ```
 
 ### Contributions are welcome...
 
 ## Feel free to show your love :heart: by putting a star :star: on this project :v: .
 
 
