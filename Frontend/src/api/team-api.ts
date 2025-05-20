import { TeamDto } from "../dtos/team-dto";
import { Team } from "../entities/team";
import { http } from "./common";

const BASE_PATH = "/api/team";

export const findAllTeams = async (): Promise<Team[]> => {
  const data = await http.get(`${BASE_PATH}/all`);

  return data.data;
};

export const fetchTeam = async (teamId: string): Promise<Team> => {
  const response = await http.get(`${BASE_PATH}/id/${teamId}`);
  return response.data;
};

export const fetchTeams = async (): Promise<TeamDto[]> => {
  try {
    const { data } = await http.get(`${BASE_PATH}/all`);
    return data;
  } catch (error) {
    throw new Error('Failed to fetch teams');
  }
};
