package uz.codial.flagsquiz

import kotlin.random.Random

object Constraints {
    private const val letter = "qwertyuioplkjhgfdsazxcvbnm"
    private val chars = letter.toCharArray()
    fun loadData(): ArrayList<QuestionData> {
        val list = ArrayList<QuestionData>()
        list.add(QuestionData(R.drawable.germany, "germany", generateLetters("germany")))
        list.add(QuestionData(R.drawable.poland, "poland", generateLetters("poland")))
        list.add(QuestionData(R.drawable.russia, "russia", generateLetters("russia")))
        list.add(QuestionData(R.drawable.denmark, "denmark", generateLetters("denmark")))
        list.add(QuestionData(R.drawable.france, "france", generateLetters("france")))
        list.add(QuestionData(R.drawable.turkey, "turkey", generateLetters("turkey")))
        list.add(QuestionData(R.drawable.kuwait, "kuwait", generateLetters("kuwait")))
        list.add(QuestionData(R.drawable.peru, "peru", generateLetters("peru")))
        list.add(QuestionData(R.drawable.spain, "spain", generateLetters("spain")))
        list.add(QuestionData(R.drawable.sweden, "sweden", generateLetters("sweden")))
//        list.add(QuestionData(R.drawable.canada, "canada", generateLetters("canada")))
//        list.add(QuestionData(R.drawable.libya, "libya", generateLetters("libya")))
//        list.add(QuestionData(R.drawable.tibet, "tibet", generateLetters("tibet")))
//        list.add(QuestionData(R.drawable.iraq, "iraq", generateLetters("iraq")))
//        list.add(QuestionData(R.drawable.cuba, "cuba", generateLetters("cuba")))
//        list.add(QuestionData(R.drawable.sudan, "sudan", generateLetters("sudan")))
//        list.add(QuestionData(R.drawable.aruba, "aruba", generateLetters("aruba")))
//        list.add(QuestionData(R.drawable.tonga, "tonga", generateLetters("tonga")))
//        list.add(QuestionData(R.drawable.greece, "greece", generateLetters("greece")))
//        list.add(QuestionData(R.drawable.albania, "albania", generateLetters("albania")))
//        list.add(QuestionData(R.drawable.iran, "iran", generateLetters("iran")))
//        list.add(QuestionData(R.drawable.mali, "mali", generateLetters("mali")))
//        list.add(QuestionData(R.drawable.zambia, "zambia", generateLetters("zambia")))
//        list.add(QuestionData(R.drawable.brazil, "brazil", generateLetters("brazil")))
//        list.add(QuestionData(R.drawable.niger, "niger", generateLetters("niger")))
//        list.add(QuestionData(R.drawable.moldova, "moldova", generateLetters("moldova")))
//        list.add(QuestionData(R.drawable.kosovo, "kosovo", generateLetters("kosovo")))
//        list.add(QuestionData(R.drawable.chad, "chad", generateLetters("chad")))
//        list.add(QuestionData(R.drawable.monaco, "monaco", generateLetters("monaco")))
//        list.add(QuestionData(R.drawable.wales, "wales", generateLetters("wales")))
//        list.add(QuestionData(R.drawable.chile, "chile", generateLetters("chile")))
//        list.add(QuestionData(R.drawable.kenya, "kenya", generateLetters("kenya")))
//        list.add(QuestionData(R.drawable.china, "china", generateLetters("china")))
//        list.add(QuestionData(R.drawable.togo, "togo", generateLetters("togo")))
//        list.add(QuestionData(R.drawable.benin, "benin", generateLetters("benin")))
//        list.add(QuestionData(R.drawable.nigeria, "nigeria", generateLetters("nigeria")))
//        list.add(QuestionData(R.drawable.yemen, "yemen", generateLetters("yemen")))
//        list.add(QuestionData(R.drawable.brunei, "brunei", generateLetters("brunei")))
//        list.add(QuestionData(R.drawable.belarus, "belarus", generateLetters("belarus")))
//        list.add(QuestionData(R.drawable.haiti, "haiti", generateLetters("haiti")))
//        list.add(QuestionData(R.drawable.oman, "oman", generateLetters("oman")))
//        list.add(QuestionData(R.drawable.malta, "malta", generateLetters("malta")))
//        list.add(QuestionData(R.drawable.iceland, "iceland", generateLetters("iceland")))
//        list.add(QuestionData(R.drawable.laos, "laos", generateLetters("laos")))
//        list.add(QuestionData(R.drawable.israel, "israel", generateLetters("israel")))
//        list.add(QuestionData(R.drawable.serbia, "serbia", generateLetters("serbia")))
//        list.add(QuestionData(R.drawable.uganda, "uganda", generateLetters("uganda")))
//        list.add(QuestionData(R.drawable.jamaica, "jamaica", generateLetters("jamaica")))
//        list.add(QuestionData(R.drawable.egypt, "egypt", generateLetters("egypt")))
//        list.add(QuestionData(R.drawable.latvia, "latvia", generateLetters("latvia")))
//        list.add(QuestionData(R.drawable.nepal, "nepal", generateLetters("nepal")))
//        list.add(QuestionData(R.drawable.ukraine, "ukraine", generateLetters("ukraine")))
//        list.add(QuestionData(R.drawable.panama, "panama", generateLetters("panama")))
//        list.add(QuestionData(R.drawable.armenia, "armenia", generateLetters("armenia")))
//        list.add(QuestionData(R.drawable.algeria, "algeria", generateLetters("algeria")))
//        list.add(QuestionData(R.drawable.ireland, "ireland", generateLetters("ireland")))
//        list.add(QuestionData(R.drawable.bahrain, "bahrain", generateLetters("bahrain")))
//        list.add(QuestionData(R.drawable.estonia, "estonia", generateLetters("estonia")))
//        list.add(QuestionData(R.drawable.pakistan, "pakistan", generateLetters("pakistan")))
//        list.add(QuestionData(R.drawable.jordan, "jordan", generateLetters("jordan")))
//        list.add(QuestionData(R.drawable.georgia, "georgia", generateLetters("georgia")))
//        list.add(QuestionData(R.drawable.finland, "finland", generateLetters("finland")))
//        list.add(QuestionData(R.drawable.ghana, "ghana", generateLetters("ghana")))
//        list.add(QuestionData(R.drawable.hungary, "hungary", generateLetters("hungary")))
//        list.add(QuestionData(R.drawable.croatia, "croatia", generateLetters("croatia")))
//        list.add(QuestionData(R.drawable.senegal, "senegal", generateLetters("senegal")))
//        list.add(QuestionData(R.drawable.taiwan, "taiwan", generateLetters("taiwan")))
//        list.add(QuestionData(R.drawable.austria, "austria", generateLetters("austria")))
//        list.add(QuestionData(R.drawable.qatar, "qatar", generateLetters("qatar")))
//        list.add(QuestionData(R.drawable.italy, "italy", generateLetters("italy")))
//        list.add(QuestionData(R.drawable.england, "england", generateLetters("england")))
//        list.add(QuestionData(R.drawable.belgium, "belgium", generateLetters("belgium")))
//        list.add(QuestionData(R.drawable.japan, "japan", generateLetters("japan")))

        return list
    }

    private fun generateLetters(str: String): String {

        var result = str
        for (i in 0 until 12 - str.length) {
                result += findLetter(str)
        }

        val list = result.toCharArray()
        list.shuffle()
        result = ""
        for (i in 0 until 12) {
            result += (list[i])
        }

        return result


    }

    private fun findLetter(str:String): Char {
        val char = chars[Random.nextInt(0, 26)]
        if (str.contains(char)){
            findLetter(str)
        }
       return char
    }
}