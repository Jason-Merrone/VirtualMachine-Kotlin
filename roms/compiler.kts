import java.io.File
val bytes = mutableListOf<Byte>()
val outfile = File("tictactoe.out")

File("tictactoe.d5700").forEachLine {
    bytes.add(it.substring(0,2).lowercase().toInt(16).toByte())
    bytes.add(it.substring(2,4).lowercase().toInt(16).toByte())
}

outfile.writeBytes(bytes.toByteArray())