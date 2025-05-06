package org.vietnamsea.authentication.service;

import java.awt.image.BufferedImage;

public interface AuthService {
    BufferedImage register2FaAuthentication(int width, int height);
}
