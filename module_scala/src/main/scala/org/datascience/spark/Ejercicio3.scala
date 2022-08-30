package org.datascience.spark

// Caesar's Cipher trouble

object Ejercicio3 {
  def main(args: Array[String]): Unit = {
    val alphabet = "abcdefghijklmnopqrstuvwxyz"
    val alphabetLength = alphabet.length
    val s = "middle-Outz" //"Always-Look-on-the-Bright-Side-of-Life"     // word without cipher
    val k = 2             //5                                            // rotation factor

//    // declarative test
//    var wordCipher = ""
//    for (z <- s.toCharArray){
//      if (z == '-'){
//        wordCipher = s"${wordCipher}${z}"
//      }else {
//        if (z.isUpper){
//          val idx = alphabet.toUpperCase().indexOf(z)
//          val charRotate = alphabet.toUpperCase()((idx + k) % alphabetLength)
//          wordCipher = s"${wordCipher}${charRotate}"
//        }else{
//          val idx = alphabet.indexOf(z)
//          val charRotate = alphabet((idx + k) % alphabetLength)
//          wordCipher = s"${wordCipher}${charRotate}"
//        }
//      }
//    }

    def returnLetterCipher(letter: String): String ={
      if ((alphabet+alphabet.toUpperCase).contains(letter)){
        if (letter.charAt(0).isUpper) {
          val idx = alphabet.toUpperCase().indexOf(letter)
          val charRotate = alphabet.toUpperCase()((idx + k) % alphabetLength)
          charRotate.toString
        } else {
          val idx = alphabet.indexOf(letter)
          val charRotate = alphabet((idx + k) % alphabetLength)
          charRotate.toString
        }
      }else{
        letter
      }
    }

    val wordCipher = s
        .split("")
        .map(returnLetterCipher)
        .reduce((a1,a2) => a1+a2)

    println(wordCipher)
  }
}
