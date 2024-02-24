package by.intereson.ebookservice.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {
    public ResourceNotFoundException(Long message) {
        super(message.toString());
    }
}
