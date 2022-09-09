package org.datascience.spark.exercises

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;

object PATH {

  def getPath(path: String): String = {
    val url: URL = PATH.getClass()
      .getClassLoader()
      .getResource(path);

    val file = new File(url.getFile)

    file.getAbsolutePath
  }

  val CARS_PATH_URL = getPath("data/cars/cars.json")
  val BANDS_PATH_URL = getPath("data/bands/bands.json")
  val GUITARS_PATH_URL = getPath("data/guitars/guitars.json")

}
