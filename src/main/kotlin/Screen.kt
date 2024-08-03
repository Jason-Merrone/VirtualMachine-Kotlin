package org.example

class Screen {
    private val memory = mutableListOf<UByte>()

    private val binaryToAsciiMap = mapOf(
        "0" to '\u0000', // NUL - Null
        "1" to '\u0001',    // SOH - Start of Heading
        "10" to '\u0002',   // STX - Start of Text
        "11" to '\u0003',   // ETX - End of Text
        "100" to '\u0004',  // EOT - End of Transmission
        "101" to '\u0005',  // ENQ - Enquiry
        "110" to '\u0006',  // ACK - Acknowledge
        "111" to '\u0007',  // BEL - Bell
        "1000" to '\u0008', // BS  - Backspace
        "1001" to '\u0009', // HT  - Horizontal Tab
        "1010" to '\n',     // LF  - Line Feed
        "1011" to '\u000B', // VT  - Vertical Tab
        "1100" to '\u000C', // FF  - Form Feed
        "1101" to '\r',     // CR  - Carriage Return
        "1110" to '\u000E', // SO  - Shift Out
        "1111" to '\u000F', // SI  - Shift In
        "10000" to '\u0010', // DLE - Data Link Escape
        "10001" to '\u0011', // DC1 - Device Control 1
        "10010" to '\u0012', // DC2 - Device Control 2
        "10011" to '\u0013', // DC3 - Device Control 3
        "10100" to '\u0014', // DC4 - Device Control 4
        "10101" to '\u0015', // NAK - Negative Acknowledge
        "10110" to '\u0016', // SYN - Synchronize
        "10111" to '\u0017', // ETB - End of Transmission Block
        "11000" to '\u0018', // CAN - Cancel
        "11001" to '\u0019', // EM  - End of Medium
        "11010" to '\u001A', // SUB - Substitute
        "11011" to '\u001B', // ESC - Escape
        "11100" to '\u001C', // FS  - File Separator
        "11101" to '\u001D', // GS  - Group Separator
        "11110" to '\u001E', // RS  - Record Separator
        "11111" to '\u001F', // US  - Unit Separator
        "100000" to ' ',    // Space
        "100001" to '!',    // exclamation mark
        "100010" to '"',    // double quote
        "100011" to '#',    // number
        "100100" to '$',    // dollar
        "100101" to '%',    // percent
        "100110" to '&',    // ampersand
        "100111" to '\'',   // single quote
        "101000" to '(',    // left parenthesis
        "101001" to ')',    // right parenthesis
        "101010" to '*',    // asterisk
        "101011" to '+',    // plus
        "101100" to ',',    // comma
        "101101" to '-',    // minus
        "101110" to '.',    // period
        "101111" to '/',    // slash
        "110000" to '0',
        "110001" to '1',
        "110010" to '2',
        "110011" to '3',
        "110100" to '4',
        "110101" to '5',
        "110110" to '6',
        "110111" to '7',
        "111000" to '8',
        "111001" to '9',
        "111010" to ':',    // colon
        "111011" to ';',    // semicolon
        "111100" to '<',    // less than
        "111101" to '=',    // equality sign
        "111110" to '>',    // greater than
        "111111" to '?',    // question mark
        "1000000" to '@',   // at sign
        "1000001" to 'A',
        "1000010" to 'B',
        "1000011" to 'C',
        "1000100" to 'D',
        "1000101" to 'E',
        "1000110" to 'F',
        "1000111" to 'G',
        "1001000" to 'H',
        "1001001" to 'I',
        "1001010" to 'J',
        "1001011" to 'K',
        "1001100" to 'L',
        "1001101" to 'M',
        "1001110" to 'N',
        "1001111" to 'O',
        "1010000" to 'P',
        "1010001" to 'Q',
        "1010010" to 'R',
        "1010011" to 'S',
        "1010100" to 'T',
        "1010101" to 'U',
        "1010110" to 'V',
        "1010111" to 'W',
        "1011000" to 'X',
        "1011001" to 'Y',
        "1011010" to 'Z',
        "1011011" to '[',    // left square bracket
        "1011100" to '\\',   // backslash
        "1011101" to ']',    // right square bracket
        "1011110" to '^',    // caret / circumflex
        "1011111" to '_',    // underscore
        "1100000" to '`',    // grave / accent
        "1100001" to 'a',
        "1100010" to 'b',
        "1100011" to 'c',
        "1100100" to 'd',
        "1100101" to 'e',
        "1100110" to 'f',
        "1100111" to 'g',
        "1101000" to 'h',
        "1101001" to 'i',
        "1101010" to 'j',
        "1101011" to 'k',
        "1101100" to 'l',
        "1101101" to 'm',
        "1101110" to 'n',
        "1101111" to 'o',
        "1110000" to 'p',
        "1110001" to 'q',
        "1110010" to 'r',
        "1110011" to 's',
        "1110100" to 't',
        "1110101" to 'u',
        "1110110" to 'v',
        "1110111" to 'w',
        "1111000" to 'x',
        "1111001" to 'y',
        "1111010" to 'z',
        "1111011" to '{',    // left curly bracket
        "1111100" to '|',    // vertical bar
        "1111101" to '}',    // right curly bracket
        "1111110" to '~',    // tilde
        "1111111" to '\u007F'  // DEL - Delete
    )

    init{
        for(i in 0 until 64){
            memory.add(0.toUByte())
        }
    }

    fun refreshDisplay(){
        println("========")
        for(i in 0 until 64 step 8){
            for(j in 0 until 8){
                val character = binaryToAsciiMap[ memory[i+j].toString(2) ]
                print(character)
            }
            print("\n")
        }
    }

    fun changeDisplayedCharacter(address: Int, value: UByte) {
        memory[address] = value
        refreshDisplay()
    }
}