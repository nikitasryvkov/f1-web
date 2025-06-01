import { PilotDto } from "../dtos/pilot-dto";
// import { PilotStatusDto } from "../dtos/pilot-status-dto";
import { Pilot } from "../entities/pilot";
import { PilotStatus } from "../entities/pilotStatus";
// import { Pilot } from "../entities/pilot";
import { http } from "./common";
import { fetchCountry } from "./country-api";
import { fetchTeam } from "./team-api";

const BASE_PATH = "/api/pilot";

export const findAllPilots = async (): Promise<PilotDto[]> => {
  const response = await http.get(`${BASE_PATH}/all`);
  const pilotDtos: Pilot[] = response.data;

  const pilots = await Promise.all(
    pilotDtos.map(async (dto) => {
      const team = await fetchTeam(dto.teamId);
      const country = await fetchCountry(dto.countryId);
      return {
        ...dto,
        team,
        country,
      };
    })
  );

  return pilots;
};

export const createPilot = async (pilotDto: PilotDto) => {
  const data = await http.post(BASE_PATH, {
    ...pilotDto,
  });

  return data.data;
};

export const makeBlockedPilot = async (id: string): Promise<void> => {
  const data = await http.post(`${BASE_PATH}/blocked/` + id);

  return data.data;
};

export const makeUnBlockedPilot = async (id: string): Promise<void> => {
  const data = await http.post(`${BASE_PATH}/blocked/` + id);

  return data.data;
};

export const changePosition = async (
  id: string,
  status: PilotStatus
): Promise<void> => {
  const data = await http.post(
    `${BASE_PATH}/status/update/${id}?status=${encodeURIComponent(status)}`
  );

  return data.data;
};

export const deletePilot = async (id: string): Promise<void> => {
  const data = await http.delete(`${BASE_PATH}/delete/` + id);

  return data.data;
}
