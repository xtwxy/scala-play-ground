package controllers

import com.github.apuex.play.bodyparser.GsonConfig
import com.github.apuex.springbootsolution.runtime.Messages
import com.google.inject.AbstractModule
import com.google.protobuf.util.JsonFormat

class Module extends AbstractModule {
  override def configure(): Unit = {
    val registry = JsonFormat.TypeRegistry.newBuilder
      .add(Messages.getDescriptor.getMessageTypes)
      .build
    val printer = JsonFormat.printer.usingTypeRegistry(registry)
    val parser = JsonFormat.parser.usingTypeRegistry(registry)
    bind(classOf[GsonConfig]).toInstance(GsonConfig(parser, printer))
  }
}
