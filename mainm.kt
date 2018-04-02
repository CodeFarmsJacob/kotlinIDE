import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.io.FileWriter
import javax.swing.*
import kotlin.math.absoluteValue

var fileDebounce : Boolean = false
var frame = JFrame("Kotlin IDE")
var fileSelect = JFileChooser()

var TextTextArea = JTextArea(45, 50)

var fileButton = JButton("Open File").apply { addActionListener(FileClick()) }
var compileButton = JButton("Compile").apply { addActionListener(CompileClick()) }
var saveButton = JButton("Save").apply {

    addActionListener(SaveClick())
}
//var compileKbutton = JButton("Compile in Kotlin ONLY").apply {addActionListener(compileKClick())}
var scroll = JScrollPane(TextTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
private class InsertMain : AbstractAction()  {

    override fun actionPerformed(e: ActionEvent) {
        TextTextArea.append("fun main(args: Array<String>)")
    }
}

fun gui() {

    frame.apply {

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(1000, 1000)
        layout = FlowLayout()


    }

    saveButton.preferredSize = Dimension(90, 20)
    compileButton.preferredSize = Dimension(90, 20)
    fileButton.preferredSize = Dimension(90, 20)

    frame.add(fileButton)
    frame.add(compileButton)
    frame.add(saveButton)
    frame.add(scroll)
    TextTextArea.lineWrap = true
    TextTextArea.inputMap.put(KeyStroke.getKeyStroke(" shift F1"), "doNothing")
    TextTextArea.actionMap.put("doNothing", InsertMain())
    frame.apply { isVisible = true }
}


private class SaveClick : ActionListener {

    override fun actionPerformed(e: ActionEvent) {

        println("Save Initialized")
        if(fileDebounce) {
           var leFile = fileSelect.selectedFile
            leFile.bufferedWriter().use { out->
                out.write(TextTextArea.text)
            }

            var writer = FileWriter(leFile, false)
            TextTextArea.write(writer)
        }
    if(!fileDebounce) {
            var newFrame = JFrame("Save File")
            var fie = fileSelect.showSaveDialog(newFrame)
            newFrame.isVisible = true
            println(fileSelect.selectedFile)
        newFrame.isVisible = false
        saveThing(fileSelect.selectedFile)
        }
    }
}
fun saveThing(file : File) {

    file.bufferedWriter().use{ out->
        out.write(TextTextArea.text)
    }
    var writer = FileWriter(file, false)
    TextTextArea.write(writer)
}
private class CompileClick : ActionListener {

    override fun actionPerformed(e: ActionEvent) {

    println("Compile Initialized")
        var newFrame = JFrame("Save File")
        var fie = fileSelect.showSaveDialog(newFrame)
        newFrame.isVisible = true
        var toUseyeet = fileSelect.selectedFile
        var toUseYEEET = System.getProperty("user.name")
        println(fileSelect.selectedFile)
        newFrame.isVisible = false
        saveThing(fileSelect.selectedFile)
        File("C:/Users/$toUseYEEET/Downloads/compileKotlin.bat").printWriter().use { out->
            out.println("\"%Desktop%/kotlinc/bin/kotlinc\" -include-runtime $toUseyeet -d \"C:/Users/$toUseYEEET/Desktop/output.jar\"")

            out.println("del compileKotlin.bat")
        }

        Runtime.getRuntime().exec("cmd.exe start /C C:/tmp/compileKotlin.bat")


    }
}

private class FileClick : ActionListener {

    override fun actionPerformed(e: ActionEvent) {

        println("File Open Initialized")
        var newFrame = JFrame("Select File")
        var fileResult = fileSelect.showOpenDialog(newFrame)
        newFrame.add(fileSelect)
        newFrame.isVisible = true

        if (fileResult == JFileChooser.APPROVE_OPTION) {
            var FileToOpen = fileSelect.selectedFile
            println("$FileToOpen Selected")
            newFrame.isVisible = false

            if(fileSelect.selectedFile.exists()) {
                var FileText = FileToOpen.readText()
                scroll.layout =  ScrollPaneLayout()
                TextTextArea.text = FileText
                frame.isVisible = false
                frame.isVisible = true
                fileDebounce = true
            }

            if (fileResult == JFileChooser.CANCEL_OPTION) {

                newFrame.isVisible = false
            }


        }
    }
}


fun main(args: Array<String>) {
    gui()
}
