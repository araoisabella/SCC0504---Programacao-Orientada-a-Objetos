//Isabella Arao 9265732, Marina Fagundes 9265405

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * The InteractiveColorMixer class is a Java Swing application that allows users to mix colors
 * using RGB sliders and view the resulting color in real-time. It also detects mouse events
 * on the color display panel.
 */
public class InteractiveColorMixer extends JFrame {
    private JSlider redSlider, greenSlider, blueSlider;
    private JLabel redLabel, greenLabel, blueLabel;
    private JPanel colorPanel;
    private JButton resetButton;

    /**
     * Constructs the InteractiveColorMixer application.
     */
    public InteractiveColorMixer() {
        setTitle("Interactive Color Mixer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize sliders and labels
        redSlider = createColorSlider();
        greenSlider = createColorSlider();
        blueSlider = createColorSlider();

        redLabel = new JLabel("Red: 128");
        greenLabel = new JLabel("Green: 128");
        blueLabel = new JLabel("Blue: 128");

        // Panel to hold sliders and labels
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 2));
        controlPanel.add(redLabel);
        controlPanel.add(redSlider);
        controlPanel.add(greenLabel);
        controlPanel.add(greenSlider);
        controlPanel.add(blueLabel);
        controlPanel.add(blueSlider);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetSliders());
        controlPanel.add(resetButton);

        // Panel to show the color
        colorPanel = new JPanel();
        colorPanel.setPreferredSize(new Dimension(400, 300));
        colorPanel.setBackground(new Color(128, 128, 128));

        // Attach mouse event listeners to the color panel
        colorPanel.addMouseListener(new MouseHandler());
        colorPanel.addMouseMotionListener(new MouseMotionHandler());

        // Add panels to frame
        add(controlPanel, BorderLayout.SOUTH);
        add(colorPanel, BorderLayout.CENTER);

        // Add change listeners to sliders
        addChangeListeners();

        setVisible(true);
    }

    /**
     * Creates a JSlider for color values with a range from 0 to 255.
     *
     * @return the configured JSlider
     */
    private JSlider createColorSlider() {
        JSlider slider = new JSlider(0, 255, 128);
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    /**
     * Adds ChangeListeners to the RGB sliders to handle events.
     */
    private void addChangeListeners() {
        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                String color = "Unknown";
                if (source == redSlider) {
                    color = "Red";
                    redLabel.setText("Red: " + source.getValue());
                } else if (source == greenSlider) {
                    color = "Green";
                    greenLabel.setText("Green: " + source.getValue());
                } else if (source == blueSlider) {
                    color = "Blue";
                    blueLabel.setText("Blue: " + source.getValue());
                }
                System.out.println(color + " slider changed to " + source.getValue());
                updateColorPanel();
            }
        };

        redSlider.addChangeListener(listener);
        greenSlider.addChangeListener(listener);
        blueSlider.addChangeListener(listener);
    }

    /**
     * Updates the background color of the color panel based on the RGB slider values.
     */
    private void updateColorPanel() {
        int red = redSlider.getValue();
        int green = greenSlider.getValue();
        int blue = blueSlider.getValue();
        colorPanel.setBackground(new Color(red, green, blue));
    }

    /**
     * Resets all RGB sliders to their default value of 128.
     */
    private void resetSliders() {
        redSlider.setValue(128);
        greenSlider.setValue(128);
        blueSlider.setValue(128);
    }

    /**
     * MouseHandler is an inner class to handle mouse events on the color panel,
     * such as entering and exiting the panel.
     */
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("Mouse entered color panel");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("Mouse exited color panel");
        }
    }

    /**
     * MouseMotionHandler is an inner class to handle mouse motion events on the color panel,
     * such as moving the mouse within the panel.
     */
    private class MouseMotionHandler extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("Mouse position: (" + e.getX() + ", " + e.getY() + ")");
        }
    }

    /**
     * Main method to launch the InteractiveColorMixer application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(InteractiveColorMixer::new);
    }
}
