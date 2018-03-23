import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import javax.swing.*

var frame = JFrame("Kotlin IDE")
var fileSelect = JFileChooser()

var TextTextArea = JTextArea()
var fileButton = JButton("Open File").apply { addActionListener(FileClick()) }
var compileButton = JButton("Compile").apply { addActionListener(CompileClick()) }
var saveButton = JButton("Save").apply {

    addActionListener(SaveClick())
}


fun gui() {

    frame.apply {

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(800, 500)
        layout = FlowLayout()


    }

    saveButton.preferredSize = Dimension(90, 20)
    compileButton.preferredSize = Dimension(90, 20)
    fileButton.preferredSize = Dimension(90, 20)
    TextTextArea.preferredSize = Dimension(600, 400)
    frame.add(fileButton)
    frame.add(compileButton)
    frame.add(saveButton)
    frame.add(TextTextArea)

    frame.apply { isVisible = true }
}


private class SaveClick : ActionListener {

    override fun actionPerformed(e: ActionEvent) {

        println("Save Initialized")


    }
}

private class CompileClick : ActionListener {

    override fun actionPerformed(e: ActionEvent) {

    println("Compile Initialized")

    }
}

private class FileClick : ActionListener {

    override fun actionPerformed(e: ActionEvent) {

        println("File Open Initialized")
        var newFrame = JFrame("Select File")
        var fileResult = fileSelect.showOpenDialog(newFrame)
        newFrame.add(fileSelect)
        newFrame.isVisible = true

    if(fileResult == JFileChooser.APPROVE_OPTION) {

        var FileToOpen = fileSelect.selectedFile
        println("$FileToOpen Selected")
        newFrame.isVisible = false
        var FileText = File(FileToOpen).read

        TextTextArea.text = FileText


        if(fileResult == JFileChooser.CANCEL_OPTION) {

            newFrame.isVisible = false
        }



    }
}



fun main(args: Array<String>) {
    gui()
}}