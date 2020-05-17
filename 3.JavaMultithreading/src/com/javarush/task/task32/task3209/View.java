package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame implements ActionListener {

    private Controller controller;
    //панель с двумя вкладками
    private JTabbedPane tabbedPane = new JTabbedPane();
    //это будет компонент для визуального редактирования html.
    //Он будет размещен на первой вкладке.
    private JTextPane htmlTextPane = new JTextPane();
    //это будет компонент для редактирования html в виде текста,
    // он будет отображать код html (теги и их содержимое).
    private JEditorPane plainTextPane = new JEditorPane();
    private Object JScrollPane;

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener=new UndoListener(undoManager);

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        //По этой строке ты можешь понять какой пункт меню создал данное событие.
        if (command.equals("Новый")) {
            controller.createNewDocument();
        } else if (command.equals("Открыть")) {
            controller.openDocument();
        } else if (command.equals("Сохранить")) {
            controller.saveDocument();
        } else if (command.equals("Сохранить как...")) {
            controller.saveDocumentAs();
        } else if (command.equals("Выход")) {
            controller.exit();
        } else if (command.equals("О программе")) {
            showAbout();
        }
    }

    public void init() {
        initGui();
        this.addWindowListener(new FrameListener(this));
        this.setVisible(true);

    }
    public void exit() {
        controller.exit();
    }

    //2 метода ниже будут отвечать за инициализацию меню и панелей редактора.
    public void initMenuBar() {
        //Создавать новый объект типа JMenuBar. Это и будет наша панель меню.
        JMenuBar jMenuBar = new JMenuBar();
        //С помощью MenuHelper инициализировать меню в следующем порядке:
        // Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь.
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        //Добавлять в верхнюю часть панели контента текущего фрейма нашу панель меню,
        // аналогично тому, как это мы делали с панелью вкладок.
        getContentPane().add(jMenuBar, BorderLayout.NORTH);

    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        //new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        //new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(300, 300));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }


    //initGui будет инициализировать графический интерфейс.
    public void initGui() {
        initMenuBar(); //вызов инициализации меню
        initEditor(); //вызов редактора
        pack(); //метод унаследованный от JFrame //устанавливает оптимальный размер для диалоговых окон
    }

    public void selectedTabChanged() {
        //Этот метод вызывается, когда произошла смена выбранной вкладки
        switch (tabbedPane.getSelectedIndex()) {
            case 0:
                controller.setPlainText(plainTextPane.getText());
                break;
            case 1:
                plainTextPane.setText(controller.getPlainText());
                break;
        }
        resetUndo();
        //Итак:
        //18.1. Метод должен проверить, какая вкладка сейчас оказалась выбранной.
        //18.2. Если выбрана вкладка с индексом 0 (html вкладка),
        // значит нам нужно получить текст из plainTextPane и установить его в контроллер с помощью метода setPlainText.
        //18.3. Если выбрана вкладка с индексом 1 (вкладка с html текстом),
        // то необходимо получить текст у контроллера с помощью метода getPlainText() и установить его в панель plainTextPane.
    }

    public boolean canUndo() {

        return undoManager.canUndo();
    }
    public boolean canRedo() {
        return undoManager.canRedo();
    }
    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }

    }
    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        //Он должен возвращать true, если выбрана вкладка,
        // отображающая html в панели вкладок (подсказка: ее индекс 0).
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        this.resetUndo();
    }

    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(null, "Версия 1.0", "Информация", JOptionPane.INFORMATION_MESSAGE);
    }

}
