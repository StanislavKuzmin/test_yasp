package com.github.stanislav.kuzmin.yasp.error;

import org.springframework.lang.NonNull;

public class AppInitException extends RuntimeException {
    public AppInitException(@NonNull String message) {
        super(message);
    }
}
