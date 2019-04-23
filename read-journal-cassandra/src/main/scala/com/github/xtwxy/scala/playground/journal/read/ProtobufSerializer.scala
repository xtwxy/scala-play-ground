package com.github.xtwxy.scala.playground.journal.read

import java.lang.reflect.Method
import java.util.concurrent.atomic.AtomicReference

import scala.annotation.tailrec

object ProtobufSerializer {
  private val ARRAY_OF_BYTE_ARRAY = Array[Class[_]](classOf[Array[Byte]])
}

class ProtobufSerializer {

  private val parsingMethodBindingRef = new AtomicReference[Map[Class[_], Method]](Map.empty)
  private val toByteArrayMethodBindingRef = new AtomicReference[Map[Class[_], Method]](Map.empty)

  def includeManifest: Boolean = true

  def fromBinary(bytes: Array[Byte], manifest: Option[Class[_]]): AnyRef = {
    manifest match {
      case Some(clazz) ⇒
        @tailrec
        def parsingMethod(method: Method = null): Method = {
          val parsingMethodBinding = parsingMethodBindingRef.get()
          parsingMethodBinding.get(clazz) match {
            case Some(cachedParsingMethod) ⇒ cachedParsingMethod
            case None ⇒
              val unCachedParsingMethod =
                if (method eq null) clazz.getDeclaredMethod("parseFrom", ProtobufSerializer.ARRAY_OF_BYTE_ARRAY: _*)
                else method
              if (parsingMethodBindingRef.compareAndSet(parsingMethodBinding, parsingMethodBinding.updated(clazz, unCachedParsingMethod)))
                unCachedParsingMethod
              else
                parsingMethod(unCachedParsingMethod)
          }
        }

        parsingMethod().invoke(null, bytes)

      case None ⇒ throw new IllegalArgumentException("Need a protobuf message class to be able to serialize bytes using protobuf")
    }
  }

  def toBinary(obj: AnyRef): Array[Byte] = {
    val clazz = obj.getClass

    @tailrec
    def toByteArrayMethod(method: Method = null): Method = {
      val toByteArrayMethodBinding = toByteArrayMethodBindingRef.get()
      toByteArrayMethodBinding.get(clazz) match {
        case Some(cachedtoByteArrayMethod) ⇒ cachedtoByteArrayMethod
        case None ⇒
          val unCachedtoByteArrayMethod =
            if (method eq null) clazz.getMethod("toByteArray")
            else method
          if (toByteArrayMethodBindingRef.compareAndSet(toByteArrayMethodBinding, toByteArrayMethodBinding.updated(clazz, unCachedtoByteArrayMethod)))
            unCachedtoByteArrayMethod
          else
            toByteArrayMethod(unCachedtoByteArrayMethod)
      }
    }

    toByteArrayMethod().invoke(obj).asInstanceOf[Array[Byte]]
  }
}
