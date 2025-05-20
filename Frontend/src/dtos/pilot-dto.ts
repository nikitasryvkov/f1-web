import { Country } from "../entities/country";
import { PilotStatus } from "../entities/pilotStatus";
import { Team } from "../entities/team";

export type PilotDto = {
  id: string;
  firstName: string;
  secondName: string;
  team: Team;
  status: PilotStatus;
  number: number;
  country: Country;
  blocked: boolean;
};
