package io.glassdoor

import io.glassdoor.application.{Constant, Context}
import io.glassdoor.interface.CommandLineInterface
import io.glassdoor.plugin.Plugin
import io.glassdoor.plugin.plugins.loader.apk.ApkLoader
import io.glassdoor.plugin.plugins.preprocessor.extractor.Extractor
import io.glassdoor.plugin.plugins.preprocessor.smali.SmaliDisassembler

object Main {
  def main(args:Array[String]):Unit={
    println("the first line of glassdoor!")
    var context = new Context

    //val ui = new CommandLineInterface()
    //ui.test()

    //TODO: these plugins should be found dynamically
    //TODO: info about the plugins should be loaded via a manifest file (as main class)
    val apkLoader:Plugin = new ApkLoader
    //TODO: these calls should be done by the pluginmanager
    apkLoader.apply(context, Array("/home/flosch/glassdoor-testset/dvel.apk"))
    //TODO: allow async callbacks here
    context = apkLoader.result
    println("loaded apk: " + context.originalBinary(Constant.ORIGINAL_BINARY_APK))

    println("trying to extract apk..")

    //extracting the dex files from the apk
    val extractor:Plugin = new Extractor
    extractor.apply(context, Array("""^.*\.[Dd][Ee][Xx]$""","intermediate-assembly.dex"))
    //extractor.apply(context,Array("""^.*\.[Ss][Oo]$""","intermediate-assembly.so"))
    context = extractor.result

//    val smali:Plugin = new SmaliDisassembler
//    smali.apply(context,Array())
//    context = smali.result

    println("extracted dex: " + context.intermediateAssembly(Constant.INTERMEDIATE_ASSEMBLY_DEX))
  }
}