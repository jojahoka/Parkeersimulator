package parkeersimulator.view;

import parkeersimulator.model.Car;
import parkeersimulator.model.Location;
import parkeersimulator.model.Model;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;


    public class CarParkView extends AbstractView {


        private Dimension size;
        private Image carParkImage;

        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView(Model model ) {
            super(model);
            setBorder(new EmptyBorder(30,30,30,30));





            size = new Dimension(0, 0);
        }


        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(1000, 500);
        }

        /**
         * Overridden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }

            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }

        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < model.getNumberOfFloors(); floor++) {
                for(int row = 0; row < model.getNumberOfRows(); row++) {
                    for(int place = 0; place < model.getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = model.getCarAt(location);
                        Color color = car == null ? Color.white : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();
        }

        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }


    }


