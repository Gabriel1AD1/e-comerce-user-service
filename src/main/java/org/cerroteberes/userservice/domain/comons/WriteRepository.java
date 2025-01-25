package org.cerroteberes.userservice.domain.comons;

/**
 * Interface genérica para operaciones de escritura en un repositorio.
 * <p>
 * Esta interfaz define las operaciones básicas necesarias para realizar
 * modificaciones sobre entidades persistentes en un repositorio.
 * Se puede usar como una base para implementar patrones de repositorio
 * en sistemas donde se utilicen operaciones genéricas para múltiples entidades.
 * </p>
 *
 * @param <M> El tipo de la entidad que será manejada por el repositorio.
 * @param <I> El tipo del identificador único de la entidad.
 */
public interface WriteRepository<M, I> {

    /**
     * Guarda una entidad en el repositorio.
     * <p>
     * Si la entidad ya existe, se actualiza. Si no existe, se crea una nueva entrada.
     * </p>
     *
     * @param entity La entidad a guardar o actualizar.
     * @return La entidad guardada o actualizada.
     */
    M save(M entity);

    /**
     * Elimina una entidad del repositorio en base a su identificador.
     * <p>
     * Si no se encuentra una entidad con el identificador proporcionado, no se realiza ninguna acción.
     * </p>
     *
     * @param id El identificador único de la entidad a eliminar.
     */
    void delete(I id);
}
