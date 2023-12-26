// Autogenerated from Pigeon (v14.0.1), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.rohit.ble_peripheral

import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  if (exception is FlutterError) {
    return listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    return listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

private fun createConnectionError(channelName: String): FlutterError {
  return FlutterError("channel-error",  "Unable to establish connection on channel: '$channelName'.", "")}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class FlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

/**
 * Models
 *
 * Generated class from Pigeon that represents data sent in messages.
 */
data class BleService (
  val uuid: String,
  val primary: Boolean,
  val characteristics: List<BleCharacteristic?>

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): BleService {
      val uuid = list[0] as String
      val primary = list[1] as Boolean
      val characteristics = list[2] as List<BleCharacteristic?>
      return BleService(uuid, primary, characteristics)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      uuid,
      primary,
      characteristics,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class BleCharacteristic (
  val uuid: String,
  val properties: List<Long?>,
  val permissions: List<Long?>,
  val descriptors: List<BleDescriptor?>? = null,
  val value: ByteArray? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): BleCharacteristic {
      val uuid = list[0] as String
      val properties = list[1] as List<Long?>
      val permissions = list[2] as List<Long?>
      val descriptors = list[3] as List<BleDescriptor?>?
      val value = list[4] as ByteArray?
      return BleCharacteristic(uuid, properties, permissions, descriptors, value)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      uuid,
      properties,
      permissions,
      descriptors,
      value,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class BleDescriptor (
  val uuid: String,
  val value: ByteArray? = null,
  val permissions: List<Long?>? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): BleDescriptor {
      val uuid = list[0] as String
      val value = list[1] as ByteArray?
      val permissions = list[2] as List<Long?>?
      return BleDescriptor(uuid, value, permissions)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      uuid,
      value,
      permissions,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class ReadRequestResult (
  val value: ByteArray,
  val offset: Long? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): ReadRequestResult {
      val value = list[0] as ByteArray
      val offset = list[1].let { if (it is Int) it.toLong() else it as Long? }
      return ReadRequestResult(value, offset)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      value,
      offset,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class ManufacturerData (
  val manufacturerId: Long,
  val data: ByteArray

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): ManufacturerData {
      val manufacturerId = list[0].let { if (it is Int) it.toLong() else it as Long }
      val data = list[1] as ByteArray
      return ManufacturerData(manufacturerId, data)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      manufacturerId,
      data,
    )
  }
}
@Suppress("UNCHECKED_CAST")
private object BlePeripheralChannelCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          BleCharacteristic.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          BleDescriptor.fromList(it)
        }
      }
      130.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          BleService.fromList(it)
        }
      }
      131.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          ManufacturerData.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is BleCharacteristic -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is BleDescriptor -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      is BleService -> {
        stream.write(130)
        writeValue(stream, value.toList())
      }
      is ManufacturerData -> {
        stream.write(131)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/**
 * Flutter -> Native
 *
 * Generated interface from Pigeon that represents a handler of messages from Flutter.
 */
interface BlePeripheralChannel {
  fun initialize()
  fun isAdvertising(): Boolean?
  fun isSupported(): Boolean
  fun stopAdvertising()
  fun askBlePermission(): Boolean
  fun addService(service: BleService)
  fun startAdvertising(services: List<String>, localName: String, timeout: Long?, manufacturerData: ManufacturerData?, addManufacturerDataInScanResponse: Boolean)
  fun updateCharacteristic(devoiceID: String, characteristicId: String, value: ByteArray)

  companion object {
    /** The codec used by BlePeripheralChannel. */
    val codec: MessageCodec<Any?> by lazy {
      BlePeripheralChannelCodec
    }
    /** Sets up an instance of `BlePeripheralChannel` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: BlePeripheralChannel?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ble_peripheral.BlePeripheralChannel.initialize", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              api.initialize()
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ble_peripheral.BlePeripheralChannel.isAdvertising", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.isAdvertising())
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ble_peripheral.BlePeripheralChannel.isSupported", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.isSupported())
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ble_peripheral.BlePeripheralChannel.stopAdvertising", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              api.stopAdvertising()
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ble_peripheral.BlePeripheralChannel.askBlePermission", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.askBlePermission())
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ble_peripheral.BlePeripheralChannel.addService", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val serviceArg = args[0] as BleService
            var wrapped: List<Any?>
            try {
              api.addService(serviceArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ble_peripheral.BlePeripheralChannel.startAdvertising", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val servicesArg = args[0] as List<String>
            val localNameArg = args[1] as String
            val timeoutArg = args[2].let { if (it is Int) it.toLong() else it as Long? }
            val manufacturerDataArg = args[3] as ManufacturerData?
            val addManufacturerDataInScanResponseArg = args[4] as Boolean
            var wrapped: List<Any?>
            try {
              api.startAdvertising(servicesArg, localNameArg, timeoutArg, manufacturerDataArg, addManufacturerDataInScanResponseArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ble_peripheral.BlePeripheralChannel.updateCharacteristic", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val devoiceIDArg = args[0] as String
            val characteristicIdArg = args[1] as String
            val valueArg = args[2] as ByteArray
            var wrapped: List<Any?>
            try {
              api.updateCharacteristic(devoiceIDArg, characteristicIdArg, valueArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
@Suppress("UNCHECKED_CAST")
private object BleCallbackCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          ReadRequestResult.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is ReadRequestResult -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/**
 * Native -> Flutter
 *
 * Generated class from Pigeon that represents Flutter messages that can be called from Kotlin.
 */
@Suppress("UNCHECKED_CAST")
class BleCallback(private val binaryMessenger: BinaryMessenger) {
  companion object {
    /** The codec used by BleCallback. */
    val codec: MessageCodec<Any?> by lazy {
      BleCallbackCodec
    }
  }
  fun onReadRequest(characteristicIdArg: String, offsetArg: Long, valueArg: ByteArray?, callback: (Result<ReadRequestResult?>) -> Unit) {
    val channelName = "dev.flutter.pigeon.ble_peripheral.BleCallback.onReadRequest"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(characteristicIdArg, offsetArg, valueArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          val output = it[0] as ReadRequestResult?
          callback(Result.success(output))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun onWriteRequest(characteristicIdArg: String, offsetArg: Long, valueArg: ByteArray?, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.ble_peripheral.BleCallback.onWriteRequest"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(characteristicIdArg, offsetArg, valueArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun onCharacteristicSubscriptionChange(deviceIdArg: String, characteristicIdArg: String, isSubscribedArg: Boolean, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.ble_peripheral.BleCallback.onCharacteristicSubscriptionChange"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(deviceIdArg, characteristicIdArg, isSubscribedArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun onAdvertisingStarted(errorArg: String?, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.ble_peripheral.BleCallback.onAdvertisingStarted"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(errorArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun onBleStateChange(stateArg: Boolean, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.ble_peripheral.BleCallback.onBleStateChange"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(stateArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun onServiceAdded(serviceIdArg: String, errorArg: String?, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.ble_peripheral.BleCallback.onServiceAdded"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(serviceIdArg, errorArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun onConnectionStateChange(deviceIdArg: String, connectedArg: Boolean, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.ble_peripheral.BleCallback.onConnectionStateChange"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(deviceIdArg, connectedArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun onBondStateChange(deviceIdArg: String, bondStateArg: Long, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.ble_peripheral.BleCallback.onBondStateChange"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(deviceIdArg, bondStateArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
}
