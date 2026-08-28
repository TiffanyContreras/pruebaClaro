output "infra_file" {
  description = "Archivo generado por Terraform"
  value       = local_file.infra_info.filename
}

output "service_name" {
  description = "Nombre del servicio"
  value       = var.service_name
}

output "service_port" {
  description = "Puerto del servicio"
  value       = var.service_port
}