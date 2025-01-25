package org.cerroteberes.userservice.domain.comons;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para operaciones de lectura en un repositorio.
 * <p>
 * Esta interfaz proporciona métodos básicos para realizar consultas
 * sobre entidades persistentes. Es útil para implementar patrones de repositorio
 * que separan las responsabilidades de lectura y escritura.
 * </p>
 *
 * @param <M> El tipo de DTO o modelo de lectura que será devuelto.
 * @param <E> El tipo de la entidad persistente asociada.
 * @param <I> El tipo del identificador único de la entidad.
 */
public interface ReadRepository<M, E, I> {

    /**
     * Busca un modelo de lectura por su identificador único.
     * <p>
     * Este método retorna un objeto opcional que contiene el modelo de lectura si se encuentra,
     * o un {@link Optional#empty()} si no existe.
     * </p>
     *
     * @param id El identificador único de la entidad.
     * @return Un {@link Optional} que contiene el modelo de lectura si se encuentra, o vacío en caso contrario.
     */
    Optional<M> findByIdRead(I id);

    /**
     * Recupera todos los modelos de lectura disponibles en el repositorio.
     * <p>
     * Este método retorna una lista con los modelos de lectura asociados a las entidades
     * persistentes.
     * </p>
     *
     * @return Una lista de modelos de lectura ({@code List<M>}). Si no hay resultados, retorna una lista vacía.
     */
    List<M> findALlRead();

    /**
     * Busca una entidad completa por su identificador único.
     * <p>
     * Este método retorna un objeto opcional que contiene la entidad persistente
     * si se encuentra, o un {@link Optional#empty()} si no existe.
     * </p>
     *
     * @param id El identificador único de la entidad.
     * @return Un {@link Optional} que contiene la entidad si se encuentra, o vacío en caso contrario.
     */
    Optional<E> findById(I id);
}
