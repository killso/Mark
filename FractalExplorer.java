import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public  class FractalExplorer {
    public int size; //Целое число «размер экрана», которое является шириной и высотой
   //  отображения в пикселях.
    public JImageDisplay pic; //Ссылка JImageDisplay, для обновления отображения в разных
    // методах в процессе вычисления фрактала.
    public FractalGenerator gen; //Объект FractalGenerator. Будет использоваться ссылка на базовый
   // класс для отображения других видов фракталов в будущем.
    public Rectangle2D.Double diapason; //Объект Rectangle2D.Double, указывающий диапазона комплексной
    // плоскости, которая выводится на экран.
    public FractalExplorer(int size1) {
        size = size1;
        diapason = new Rectangle2D.Double();
        gen = new Mandelbrot();
        gen.getInitialRange(diapason);
        pic = new JImageDisplay(size, size);
        return;
    }
    public void createAndShowGUI() {
        pic.setLayout(new BorderLayout());
        //центр
        JFrame frame = new JFrame("Фракталы");
        frame.add(pic, BorderLayout.CENTER);
        JButton resetImageButton = new JButton("Сброс");
        //низ
        Reset handler = new Reset();
        resetImageButton.addActionListener(handler);
        frame.add(resetImageButton, BorderLayout.SOUTH);
        Mouse click = new Mouse();
        pic.addMouseListener(click);
        //ресет(выход)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        return;
    }
    private void drawFractal() {
        for (int x=0; x<size; x ++)
        {
            for (int y=0; y<size; y++)
            { //x - пиксельная координата; xCoord - координата в пространстве фрактала
                double xCoord = FractalGenerator.getCoord(diapason.x, diapason.x + diapason.width,
                       size, x);
                double yCoord = FractalGenerator.getCoord(diapason.y, diapason.y + diapason.height,
                        size, y);
                int numIters = size.numIterations;
                if (numIters == -1) {
                    pic.drawPixel(x, y, 0); //пиксель в черный цвет, если точка не выходит за границы
                }
                else // я взяла вариант с цветовым пространствои HSV из файла
                    {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    pic.drawPixel(x, y, rgbColor);
                        // или же можно использовать такой вариант, основанный на количестве итерций
                        // pic.drawPixel(x, y, numIters);
                }
            }
        }
        pic.repaint();
    }
    public class Reset implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gen.getInitialRange(diapason);
            drawFractal();
        }
    }
    public class Mouse extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            double xCoord = FractalGenerator.getCoord(diapason.x, diapason.x + diapason.width,
                    size, x);
            int y = e.getY();
            double yCoord = FractalGenerator.getCoord(diapason.y, diapason.y + diapason.height,
                    size, y);
            gen.recenterAndZoomRange(diapason, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }
    public static void main(String args[]) {
        FractalExplorer start = new FractalExplorer(800);
        start.createAndShowGUI();
        start.drawFractal(); //отображение начального представления
    }
}
