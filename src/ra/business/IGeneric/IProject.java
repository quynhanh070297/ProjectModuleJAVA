package ra.business.IGeneric;

import ra.business.entity.Project;

public interface IProject extends ICrud<Project,Integer>
{
    void updateProjectStatus();

}
