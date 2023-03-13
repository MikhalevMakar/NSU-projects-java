//package org.ru.nsu.mikhalev.task3;
//
//public class FieldValidation {
//    private void shiftDown(int curRow) {
//        for (int row = curRow; row > 0; row--) {
//            for (int column = 0; column < WIDTH; ++column) {
//                placedShape[row][column] = placedShape[row - 1][column];
//            }
//        }
//    }
//
//    private boolean checkBarrier() {
//        if (shape.getY () + shape.getHeight () >= HEIGHT / (RATIO_SHAPE_VAlUE * SCALE) - 1)
//            return false;
//
//        int w = shape.getWidth ();
//        int h = shape.getHeight ();
//        for (int column = 0; column < w; ++column) {
//            for (int row = h - 1; row >= 0; --row) {
//                int x = column + shape.getX ();
//                int y = row + shape.getY ();
//                if (shape.IsShape (column, row) &&
//                        placedShape[y + 1][x] != null)
//                    return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean checkLeft() {
//        if (shape.getLeftSide () == 0)
//            return false;
//
//        int w = shape.getWidth ();
//        int h = shape.getHeight ();
//        for (int row = 0; row < h; ++row) {
//            for (int column = 0; column < w; ++column) {
//                if (shape.IsShape (column, row) &&
//                        placedShape[row + shape.getY ()][column + shape.getX () - 1] != null)
//                    return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean checkRight() {
//        if (shape.getRightSide () > WIDTH / (RATIO_SHAPE_VAlUE * SCALE))
//            return false;
//
//        int w = shape.getWidth ();
//        int h = shape.getHeight ();
//        for (int row = 0; row < h; ++row) {
//            for (int column = 0; column < w; ++column) {
//                if (shape.IsShape (column, row) &&
//                        placedShape[row + shape.getY ()][column + shape.getX () + 1] != null) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public boolean checkRotate() {
//        shape.nextRotation ();
//        if (!checkRight () || !checkLeft () || !checkBarrier ()) {
//            shape.previousRotation ();
//            return false;
//        }
//        shape.previousRotation ();
//        return true;
//    }
//
//    public boolean IsMoveShapeDown() {
//        if (!checkBarrier ()) {
//            moveShapeToBackGround();
//            clearLines ();
//            return false;
//        }
//        this.shape.moveDown();
//        repaint();
//        return true;
//    }
//}