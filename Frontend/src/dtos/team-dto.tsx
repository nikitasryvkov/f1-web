import { Country } from "../entities/country"

export type TeamDto = {
    id: string,
    title: string,
    country: Country
}