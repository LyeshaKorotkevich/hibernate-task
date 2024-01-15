package ru.clevertec.house.exception;

/**
 * Исключение, выбрасываемое при попытке доступа к несуществующему объекту.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Конструктор исключения с заданным сообщением.
     *
     * @param message Сообщение об ошибке.
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Создает исключение NotFound для указанного класса и значения поля.
     *
     * @param clazz Класс объекта.
     * @param field Значение поля, по которому объект не найден.
     * @return Исключение NotFound с информацией о классе и значении поля.
     */
    public static NotFoundException of(Class<?> clazz, Object field) {
        return new NotFoundException(clazz.getSimpleName() + " with " + field + " not found");
    }
}
