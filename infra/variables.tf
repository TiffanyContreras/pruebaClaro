variable "project_name" {
  description = "Nombre del proyecto"
  type        = string
  default     = "pruebaClaro"
}

variable "service_name" {
  description = "Nombre del microservicio"
  type        = string
  default     = "clientes-service"
}

variable "service_port" {
  description = "Puerto del microservicio"
  type        = number
  default     = 8080
}

variable "database" {
  description = "Base de datos utilizada"
  type        = string
  default     = "Oracle"
}

variable "deployment_platform" {
  description = "Plataforma de despliegue"
  type        = string
  default     = "Kubernetes"
}