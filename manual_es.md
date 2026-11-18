# Biking Pro - Manual de usuario (Español)

Estado: versión 1.3 de la aplicación. Este manual describe todas las funciones de la aplicación para el uso diario.

## 1. De qué trata

Biking Pro es una aplicación de ciclismo para Android. Graba recorridos mediante el GPS del dispositivo, muestra en directo la velocidad, la distancia y el tiempo, representa la ruta en un mapa de OpenStreetMap y gestiona tanto los recorridos como las rutas importadas en formato GPX. No hay publicidad, ni suscripción, ni cuentas de usuario; todos los datos permanecen en tu dispositivo. La interfaz es multilingüe (alemán, inglés, francés, español, danés, neerlandés, italiano); por defecto sigue el idioma del dispositivo y también puede fijarse en los ajustes (véase la sección 8).

**Requisitos del sistema**

| Requisito | Descripción |
|---|---|
| Sistema operativo | Android 8.0 o posterior |
| Ubicación | GPS o ubicación por red activada en el dispositivo |
| Permisos | Ubicación exacta (para la grabación y la visualización de la posición); notificaciones (desde Android 13, para el aviso durante la grabación) |
| Internet | Solo para cargar las teselas del mapa de OpenStreetMap; la grabación en sí también funciona sin conexión |

**Permisos necesarios de un vistazo**

| Permiso | Para qué | Cuándo se solicita |
|---|---|---|
| Ubicación exacta | Grabar el recorrido, mostrar tu posición en el mapa | Al pulsar "Start" por primera vez; después se guarda de forma permanente, salvo que la revoques |
| Notificaciones | Aviso permanente "El recorrido se está grabando …" durante el recorrido | Desde Android 13, al pulsar "Start" por primera vez |

## 2. Primer inicio: dirección de contacto para el uso de los mapas

En el primer inicio aparece un diálogo con el título "Dirección de contacto para el uso de los mapas":

1. Los mapas provienen de OpenStreetMap. Sus condiciones de uso exigen una dirección de contacto que se transmite como identificación en cada solicitud de mapa.
2. Introduce tu dirección de correo electrónico y elige "Guardar".
3. Sin una entrada válida el mapa permanece bloqueado; el diálogo no se puede cancelar.

La dirección se guarda exclusivamente de forma local en tu dispositivo, se conserva en las actualizaciones de la aplicación y no se vuelve a solicitar. No se transmite a terceros; solo se envía al servicio de mapas de OpenStreetMap como parte de la identificación. Después de borrar los datos de la aplicación (véase la sección 8), la consulta vuelve a aparecer.

## 3. La página principal de un vistazo

La página principal consta de las siguientes zonas:

| Zona | Significado |
|---|---|
| Visualización en directo | Velocidad actual (km/h), distancia (km), tiempo del recorrido, velocidad media y máxima |
| Start / Stop | Inicia y termina la grabación de un recorrido |
| Mapa | Mapa de OpenStreetMap con tu posición, una ruta planificada mostrada y la grabación en curso |
| El mapa sigue el recorrido | Interruptor: si lo mantienes activo, el mapa se desplaza automáticamente durante el recorrido |
| Recorridos anteriores | Abre la lista de todos los recorridos guardados |
| Rutas planificadas | Abre la gestión de rutas con importación GPX |
| Ajustes | Abre la página de ajustes con copia de seguridad/restauración |

El mapa admite los gestos habituales: arrastrar para desplazarlo y pellizcar o separar dos dedos para hacer zoom.

## 4. Grabar un recorrido

Así se graba un recorrido:

1. Pulsa "Start". La primera vez, la aplicación solicita el permiso de ubicación (y desde Android 13 también el de notificaciones); confírmalos.
2. La grabación continúa también en segundo plano; una notificación permanente con el aviso "El recorrido se está grabando …" lo indica.
3. La visualización en directo se actualiza continuamente durante el recorrido.
4. Al llegar al destino, pulsa "Stop". El recorrido se guarda automáticamente con todos los valores y aparece después en "Recorridos anteriores".

**Qué registra la aplicación durante el recorrido**

- Consulta de la posición en modo de alta precisión, aproximadamente cada dos segundos.
- Distancia, velocidad actual y tiempo del recorrido.
- Velocidad media y velocidad máxima.
- Velocidad mínima: solo se tienen en cuenta los valores superiores a 2 km/h, para que el ruido del GPS parado no genere valores sin sentido.
- Ascenso máximo: la aplicación suma las variaciones de altitud positivas; los metros descendidos no se restan.

**Consejos prácticos**

- Para obtener valores utilizables, muévete al aire libre; en interiores la recepción GPS suele ser demasiado débil.
- No dejes que la pantalla se bloquee por accidente mientras observas la visualización en directo; la grabación en sí continúa de todos modos.
- Si la batería está muy baja, puedes terminar la grabación antes del destino; los datos registrados hasta ese momento se guardan.

## 5. El mapa y tu posición

- **Tu posición:** punto azul con borde blanco; el círculo semitransparente alrededor del punto muestra la precisión GPS actual. El punto también es visible fuera de las grabaciones en curso.
- **Primera recepción GPS:** al iniciar la aplicación, el mapa se sitúa primero sobre Berlín (marcador de posición). En cuanto hay la primera recepción GPS, el mapa salta automáticamente una vez a tu posición; después permanece libremente utilizable.
- **Ruta planificada:** una ruta seleccionada se representa en verde neón en el mapa.
- **Grabación propia:** el recorrido en curso se dibuja en azul. Las desviaciones entre lo planificado y lo recorrido son directamente visibles.
- **El mapa sigue el recorrido:** si el interruptor está activo, la sección del mapa sigue tu posición durante la grabación.

## 6. Ver y gestionar los recorridos anteriores

**Lista:** "Recorridos anteriores" muestra todos los recorridos guardados, empezando por el más reciente. Cada entrada indica la fecha y la hora de inicio, así como la distancia, la duración y la velocidad media. Si todavía no hay ningún recorrido, aparece el aviso "Todavía no hay recorridos grabados."

**Eliminar un recorrido:** el icono de papelera a la derecha de la entrada abre una pregunta de confirmación; solo tras confirmar con "Eliminar" se elimina el recorrido de forma definitiva. La acción no se puede deshacer.

**Página de detalle:** al tocar una entrada de la lista se abre la vista detallada con:

- el mapa, en el que la ruta está dibujada y la sección se ajusta automáticamente a la ruta;
- seis valores estadísticos: distancia, duración, velocidad máxima, velocidad mínima, velocidad media y ascenso máximo.

**Exportación GPX:** el botón "Exportar como GPX" abre el diálogo de selección de archivos del sistema. Elige un lugar de almacenamiento; el archivo recibe automáticamente un nombre con el patrón `BikingPro_<fecha>_<hora>.gpx` y contiene la ruta con todos los puntos. Así puedes reutilizar el recorrido en otros programas de mapas o de entrenamiento.

## 7. Rutas planificadas (importación GPX)

**Importar:**

1. Abre "Rutas planificadas" y pulsa "Importar archivo GPX".
2. Elige un archivo GPX en el diálogo de selección. Se admiten tanto puntos de track (trkpt) como puntos de ruta (rtept).
3. La ruta aparece en la lista. Se adopta el nombre del archivo GPX; si el archivo no contiene nombre, la ruta se llama "Ruta sin nombre".

Cada entrada de la lista muestra la longitud de la ruta y el número de puntos. Si todavía no hay ninguna ruta, aparece el aviso "Todavía no hay rutas importadas."

**Mostrar en el mapa:** al tocar la ruta se muestra en verde neón en el mapa principal; un mensaje confirma la selección. Vuelve con "Atrás" a la página principal y verás la ruta planificada y tu propia grabación superpuestas en cuanto inicies un recorrido.

**Quitar selección:** el botón "Quitar selección" retira la ruta del mapa principal.

**Cambiar el nombre:** el icono de lápiz abre un diálogo con el nombre actual; tras "Guardar", la ruta aparece en la lista con su nuevo nombre.

**Eliminar:** el icono de papelera pide confirmación y elimina la ruta tras confirmarla. Si la ruta eliminada está mostrada en ese momento en el mapa, se retira automáticamente de allí. El archivo GPX original del dispositivo no se ve afectado.

## 8. Ajustes

A través del botón "Ajustes" de la página principal accedes a la página de ajustes. Comprende tres zonas: "Copia de seguridad/Restauración", "Idioma" y "Acerca de".

### Copia de seguridad y restauración

La copia de seguridad incluye todos los recorridos grabados y las rutas planificadas en un único archivo.

**Crear copia de seguridad:**

1. Pulsa "Crear copia de seguridad".
2. Se abre el diálogo de selección de archivos del sistema; elige un lugar de almacenamiento (por ejemplo, la carpeta de descargas o una nube).
3. El archivo recibe automáticamente un nombre con el patrón `biking_pro_backup_<fecha>_<hora>.db`.
4. Un mensaje confirma la finalización.

**Restaurar copia de seguridad:**

1. Pulsa "Restaurar copia de seguridad" y elige un archivo exportado anteriormente.
2. La aplicación comprueba primero el formato del archivo. Solo se aceptan copias de seguridad válidas de Biking Pro; los archivos no válidos se rechazan con un mensaje, sin modificar los datos existentes.
3. Tras una restauración correcta, todos los recorridos y rutas contenidos vuelven a estar disponibles.

**Indicaciones importantes:**

- La copia de seguridad contiene la base de datos con los recorridos y las rutas. La dirección de contacto para el uso de los mapas no forma parte del archivo; en un dispositivo nuevo se vuelve a solicitar en el primer inicio.
- Al cambiar de dispositivo, instala primero la aplicación en el dispositivo nuevo, restaura la copia de seguridad mediante "Ajustes" y registra después la dirección de contacto.
- Las actualizaciones de la aplicación sobre una APK instalada conservan en principio tus datos; una copia de seguridad es la protección adicional frente a daños, reinstalaciones o cambios de dispositivo.
- Además, la aplicación crea automáticamente una copia interna de seguridad antes de las migraciones internas de la base de datos. Este proceso no es visible para ti.

### Idioma

La zona "Idioma" muestra el idioma activo de la aplicación. Por defecto, la aplicación sigue el idioma del dispositivo (se admiten alemán, inglés, francés, español, danés, neerlandés e italiano; para todos los demás idiomas del dispositivo se aplica el inglés).

Mediante el botón "Elegir idioma" puedes elegir uno de los siete idiomas admitidos:

1. Pulsa "Elegir idioma"; aparece una selección con el idioma del sistema y todos los idiomas admitidos.
2. Elige un idioma; se aplica de inmediato a toda la aplicación, sin reiniciar.
3. La elección se guarda de forma permanente y sobrevive a los reinicios de la aplicación y del dispositivo.
4. La entrada "Idioma del sistema" restablece el comportamiento original: la aplicación vuelve a seguir el idioma del dispositivo.

Nota: desde Android 13, el idioma elegido aparece también en los ajustes del sistema en "Aplicaciones", "Biking Pro", "Idiomas" y puede modificarse allí igualmente. Los formatos numéricos, las fechas y las horas siguen siempre el idioma activo.

### Acerca de

El botón "Acerca de" abre un diálogo con el número de versión de la aplicación instalada y una referencia a este manual de usuario en el repositorio del proyecto en GitHub. El botón "Abrir el manual" del diálogo abre la página en el navegador.

## 9. Privacidad y almacenamiento de datos

- Todos los recorridos, las rutas y la dirección de contacto se guardan exclusivamente de forma local, en una base de datos o en los ajustes de la aplicación de tu dispositivo.
- La aplicación no tiene conexión con servidores, ni publicidad, ni suscripción.
- Las teselas del mapa se cargan del servicio de OpenStreetMap; la dirección de contacto registrada en la configuración inicial se transmite como identificación. No se transmiten otros datos personales.
- Elimina todos los datos borrando los datos de la aplicación en los ajustes de Android en "Aplicaciones", "Biking Pro"; la consulta de la dirección de contacto vuelve a aparecer después.

## 10. Preguntas frecuentes y soluciones

| Observación | Causa | Solución |
|---|---|---|
| Falta el punto azul de mi posición | Permiso de ubicación no concedido o sin recepción GPS | Conceder el permiso (pulsar "Start" una vez o vía Ajustes de Android, Aplicaciones, Biking Pro, Permisos); esperar la recepción GPS al aire libre |
| El mapa arranca sobre Berlín y no salta a mi posición | Todavía no hay fix GPS | Al aire libre, esperar la primera recepción GPS; el salto se produce automáticamente. Berlín es solo un marcador de posición |
| La grabación no empieza | Servicio de ubicación del dispositivo desactivado | Activar la ubicación en los ajustes rápidos de Android |
| El mapa muestra "Access blocked" | Identificación insuficiente ante OpenStreetMap | Registrar una dirección de contacto válida; si se guardó una dirección errónea, borrar los datos de la aplicación y registrar la dirección de nuevo |
| No hay notificación durante el recorrido (desde Android 13) | Permiso de notificación denegado | Conceder el permiso a posteriori en los ajustes de Android; la grabación funciona también sin notificación |
| La importación GPX indica "No se pudo leer el archivo GPX." | El archivo no contiene puntos de track ni de ruta, o está dañado | Usar otro archivo GPX; el archivo debe contener puntos de track (trkpt) o puntos de ruta (rtept) |
| La restauración indica un archivo no válido | El archivo elegido no es una copia de seguridad de Biking Pro | Usar solo archivos creados mediante "Ajustes", "Crear copia de seguridad" |
| El idioma elegido no se guarda al reiniciar la aplicación (Android 12 y anterior) | El guardado de la elección de idioma funciona internamente mediante un mecanismo de la aplicación | Repetir la elección; la versión actual de la aplicación la guarda de forma permanente. Si el problema persiste, actualizar la aplicación |
| La aplicación debe seguir de nuevo el idioma del dispositivo | Se ha elegido un idioma fijo | En los ajustes, en "Elegir idioma", seleccionar la entrada "Idioma del sistema" |
| La velocidad muestra valores irreales | Ruido del GPS, por ejemplo entre edificios | Utilizar los valores medios para la evaluación; la aplicación solo tiene en cuenta la velocidad mínima por encima de 2 km/h de todos modos |

## 11. Disponibilidad y versión

La aplicación se proporciona como archivo de instalación (APK) a través del repositorio del proyecto en github.com/Black-Perl/biking-pro. Antes de la instalación, Android debe permitir la instalación de fuentes desconocidas para tu navegador o aplicación de archivos. Las nuevas versiones se instalan simplemente sobre la instalación existente; los recorridos, las rutas y la dirección de contacto se conservan. El número de versión instalado puede verse en cualquier momento en el diálogo "Acerca de" de los ajustes de la aplicación; ese diálogo remite igualmente a este manual en el repositorio del proyecto.

Ante preguntas o problemas, dirígete a la dirección de contacto del proyecto indicada en la publicación.
