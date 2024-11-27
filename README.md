
## Calculadora de IMC

Mejoras realizadas con respecto al enunciado de la práctica:

- Se ha **añadido un `ViewModel` para separar el modelo de la vista** y actualizarla a través de ese objeto ViewModel, además, la actualización es automática cuando se modifica el modelo. Esto lo veremos más adelante, pero para que nos vaya sonando. Además, He empleado LiveData, que es una clase que se encarga de notificar a la vista cuando el modelo ha cambiado. En la actualidad también suele emplearse StateFlow, pero requiere un poco más de código (precisamos estudiar Flow)
  - https://developer.android.com/reference/androidx/lifecycle/ViewModel
- Se ha credo un tema personalizado para la aplicación, creado en: https://material-foundation.github.io/material-theme-builder/
  - https://m3.material.io/
- Se ha **personalizado el un `ImageView`, `ImageViewBarra`**, para que escriba una línea vertical dependiendo del valor del IMC.
- He **añadido un recurso de tipo font**, para cambiar la fuente de la aplicación.
- Algún cambio menor.

- Por ahora centraros en los Layouts, el binding, la gestión de eventos y el modelo.