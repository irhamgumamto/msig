package com.example.demo.constant;

public class ExceptionConstant {
    public static final String ACCOUNT_LOCKED_MSG = "Akun Anda telah dikunci. " +
            "Silakan menggunakan fitur lupa password untuk masuk ke dalam aplikasi.";
    public static final String METHOD_IS_NOT_ALLOWED_MSG = "This request method is not allowed on this endpoint. " +
            "Please send a '%s' request.";
    public static final String INTERNAL_SERVER_ERROR_MSG = "Terjadi kesalahan saat memproses permintaan di server";
    public static final String INCORRECT_CREDENTIALS = "Username / password salah. Silakan mencoba kembali";
    public static final String ACCOUNT_DISABLED = "Akun Anda telah dinonaktifkan. " +
            "Silakan menggunakan fitur lupa password untuk masuk ke dalam aplikasi.";
    public static final String ERROR_PROCESSING_FILE = "Terjadi error ketika melakukan pemrosesan file di server.";
    public static final String NOT_ENOUGH_PERMISSION = "Anda tidak diizinkan untuk mengakses";
    public static final String THIRD_PARTY_ERROR = "Terjadi error saat melakukan pemrosesan dengan aplikasi pihak ketiga";

    public static final String ERROR_PATH = "/error";
    public static final String APPID_ERROR_MSG = "You are not allowed to access The API";

    private ExceptionConstant() {
    }
}
