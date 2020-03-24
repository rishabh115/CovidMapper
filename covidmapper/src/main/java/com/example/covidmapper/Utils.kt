package com.example.covidmapper

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

internal object Utils {

    internal fun parseHtmlData(data: String?): List<CountryData>{
        if (data!=null) {
            return parseDoc(Jsoup.parse(data))
        }
        return emptyList<CountryData>()
    }

    private fun parseDoc(doc: Document): List<CountryData> {
        val list = ArrayList<CountryData>()
        val countryTable = doc.select("table").get(0)
        val countryRow = countryTable.select("tbody").get(0)
        for (countryItem in countryRow.children()) {

            with(countryItem){
                val countryName = child(0).text()
                val totalCases = generateValidInt(child(1).text())
                val newCases = generateValidInt(child(2).text())
                val totalDeaths = generateValidInt(child(3).text())
                val newDeaths = generateValidInt(child(4).text())
                val totalRecovered = generateValidInt(child(5).text())
                val activeCases = generateValidInt(child(6).text())
                val criticalCases = generateValidInt(child(7).text())
                val listItem = CountryData(countryName, totalCases, newCases, totalDeaths, newDeaths,
                    totalRecovered, activeCases, criticalCases)

                list.add(listItem)
            }
        }
        return list
    }

    private fun generateValidInt(s: String?): Int{
        if (s==null || s.trim().equals("")){
            return 0;
        }
        val filtered = s.replace(",","").replace("+","")
        return Integer.parseInt(filtered)
    }
}

val <T> T.exhaustiveCall: T
    get() = this