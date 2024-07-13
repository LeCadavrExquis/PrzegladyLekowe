import React, { useState } from 'react';

import { Doctor } from './Doctor';
import { User } from './User';
import { RoleChoice } from './RoleChoice';
import { UserForm } from './UserForm';


const Dashboard = () => {

  const [currentComponent, setCurrentComponent] = useState('roleChoice');

  const switchComponent = (component) => {
    setCurrentComponent(component);
  };

  const renderComponent = () => {
    switch (currentComponent) {

        case 'roleChoice':
            return <RoleChoice switchComponent={switchComponent} />;

        case 'doctor':
            return <Doctor switchComponent={switchComponent} />;

        case 'user':
            return <User switchComponent={switchComponent} />;

        case 'userForm':
            return <UserForm switchComponent={switchComponent} />;
        
        default:
            return null;
    }
  }

  return (
    <div>
       {renderComponent()}
    </div>
  );
};

export {Dashboard};
