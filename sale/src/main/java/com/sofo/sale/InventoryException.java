package com.sofo.sale;

/**
 * Created by sofo on 2017/09/24.
 */
public class InventoryException extends Exception{
        private static final long serialVersionUID = 1L;
        public InventoryException(final String message) {
            super(message);
        }
}
